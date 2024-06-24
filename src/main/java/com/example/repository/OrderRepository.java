package com.example.repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注文（Order）に関するデータアクセスを行うリポジトリクラスです.
 *
 * @author reon.hatsuda
 */
@Repository
public class OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * ResultSetからOrderオブジェクトを抽出するためのResultSetExtractorです。
     */
    private static final ResultSetExtractor<Order> ORDER_RESULT_SET_EXTRACTOR = new ResultSetExtractor<Order>() {
        @Override
        public Order extractData(ResultSet rs) throws SQLException {
            Order order = null;
            Map<Integer, OrderItem> orderItemMap = new HashMap<>();

            while (rs.next()) {
                if (order == null) {
                    order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setStatusId(rs.getInt("status_id"));
                    order.setTotalPrice(rs.getInt("total_price"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setPaymentMethodId(rs.getInt("payment_method_id"));
                    order.setDeliveryDate(rs.getDate("delivery_date"));
                    order.setItemList(new ArrayList<>());
                }

                int orderItemId = rs.getInt("order_item_id");
                if (!orderItemMap.containsKey(orderItemId) && orderItemId != 0) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(orderItemId);
                    orderItem.setOrderId(rs.getInt("order_id"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItem.setSize(rs.getString("size"));

                    Item item = new Item();
                    item.setId(rs.getInt("item_id"));
                    item.setName(rs.getString("item_name"));
                    item.setDescription(rs.getString("item_description"));
                    item.setPrice(rs.getInt("item_price"));
                    item.setItemType(rs.getString("item_item_type"));
                    item.setImagePath(rs.getString("item_image_path"));

                    orderItem.setItem(item);
                    orderItemMap.put(orderItemId, orderItem);
                    order.getItemList().add(orderItem);
                }
            }
            return order;
        }
    };


    /**
     * ResultSetからOrderオブジェクトを抽出するためのResultSetExtractorです。
     */
    private static final ResultSetExtractor<List<Order>> ORDER_LIST_RESULT_SET_EXTRACTOR = new ResultSetExtractor<List<Order>>() {
        @Override
        public List<Order> extractData(ResultSet rs) throws SQLException {
            Map<Integer, Order> orderMap = new HashMap<>();
            Map<Integer, Item> itemMap = new HashMap<>();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");

                Order order = orderMap.get(orderId);
                if (order == null) {
                    order = new Order();
                    order.setId(orderId);
                    order.setUserId(rs.getInt("user_id"));
                    order.setStatusId(rs.getInt("status_id"));
                    order.setTotalPrice(rs.getInt("total_price"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setPaymentMethodId(rs.getInt("payment_method_id"));
                    order.setDeliveryDate(rs.getDate("delivery_date"));
                    order.setItemList(new ArrayList<>());
                    orderMap.put(orderId, order);
                }

                int orderItemId = rs.getInt("order_item_id");
                if (orderItemId > 0) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(orderItemId);
                    orderItem.setOrderId(orderId);
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItem.setSize(rs.getString("size"));

                    int itemId = rs.getInt("item_id");
                    Item item = itemMap.get(itemId);
                    if (item == null) {
                        item = new Item();
                        item.setId(itemId);
                        item.setName(rs.getString("item_name"));
                        item.setDescription(rs.getString("item_description"));
                        item.setPrice(rs.getInt("item_price"));
                        item.setItemType(rs.getString("item_item_type"));
                        item.setImagePath(rs.getString("item_image_path"));
                        itemMap.put(itemId, item);
                    }
                    orderItem.setItem(item);

                    order.getItemList().add(orderItem);
                }
            }

            return new ArrayList<>(orderMap.values());
        }
    };




    /**
     * ResultSetからOrderオブジェクトをマッピングするためのRowMapperです。
     */
    private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, rowNum) -> {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setStatusId(rs.getInt("status_id"));
        order.setTotalPrice(rs.getInt("total_price"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        order.setPaymentMethodId(rs.getInt("payment_method_id"));
        order.setDeliveryDate(rs.getDate("delivery_date"));
        return order;
    };

    /**
     * 指定した注文IDに対応するOrderオブジェクトを取得します。
     *
     * @param orderId 注文ID
     * @return 注文オブジェクト。見つからない場合はnull。
     */
    public Order findById(Integer orderId) {
        String sql = """
            SELECT 
                o.id AS order_id, 
                o.user_id, 
                o.status_id, 
                o.total_price, 
                o.order_date, 
                o.payment_method_id, 
                o.delivery_date, 
                o.address_id,
                oi.id AS order_item_id,
                oi.item_id,
                oi.quantity,
                oi.size,
                i.id AS item_id,
                i.name AS item_name,
                i.description AS item_description,
                i.price AS item_price,
                i.item_type AS item_item_type,
                i.image_path AS item_image_path
            FROM 
                Orders o
            LEFT JOIN 
                OrderItems oi ON o.id = oi.order_id
            LEFT JOIN 
                Items i ON oi.item_id = i.id
            WHERE 
                o.id = :orderId
            """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
        return template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
    }

    /**
     * 指定したユーザIDに対応するOrderオブジェクトを複数取得します。
     *
     * @param userId ユーザID
     * @return 注文オブジェクトのリスト。
     */
    public List<Order> findByUserId(Integer userId) {
        String sql = """
            SELECT 
                o.id AS order_id, 
                o.user_id, 
                o.status_id, 
                o.total_price, 
                o.order_date, 
                o.payment_method_id, 
                o.delivery_date, 
                o.address_id,
                oi.id AS order_item_id,
                oi.item_id,
                oi.quantity,
                oi.size,
                i.id AS item_id,
                i.name AS item_name,
                i.description AS item_description,
                i.price AS item_price,
                i.item_type AS item_item_type,
                i.image_path AS item_image_path
            FROM 
                Orders o
            LEFT JOIN 
                OrderItems oi ON o.id = oi.order_id
            LEFT JOIN 
                Items i ON oi.item_id = i.id
            WHERE 
                o.user_id = :userId
            """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        return template.query(sql, param, ORDER_LIST_RESULT_SET_EXTRACTOR);
    }

    /**
     * 指定したユーザーIDに対応するアクティブな（ステータスIDが1の）Orderオブジェクトを取得します。
     *
     * @param userId ユーザーID
     * @return アクティブなOrderオブジェクト。見つからない場合はnull。
     */
    public Order findActiveOrderByUserId(Integer userId) {
        String sql = """
                SELECT 
                o.id AS order_id, 
                o.user_id, 
                o.status_id, 
                o.total_price, 
                o.order_date, 
                o.payment_method_id, 
                o.delivery_date, 
                o.address_id,
                oi.id AS order_item_id,
                oi.item_id,
                oi.quantity,
                oi.size,
                i.id AS item_id,
                i.name AS item_name,
                i.description AS item_description,
                i.price AS item_price,
                i.item_type AS item_item_type,
                i.image_path AS item_image_path
            FROM 
                Orders o
            LEFT JOIN 
                OrderItems oi ON o.id = oi.order_id
            LEFT JOIN 
                Items i ON oi.item_id = i.id
            WHERE 
               o.user_id = :userId AND o.status_id = 1
                """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        Order order = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);

        if (order != null) {
            return order;
        } else {
            return null;
        }
    }

    /**
     * 新しいOrderを作成し、そのIDを返します。
     *
     * @param userId ユーザーID
     * @return 作成されたOrderのID
     */
    public Integer createOrder(Integer userId) {
        String sql = """
            INSERT INTO Orders (user_id, status_id, total_price, order_date, payment_method_id, delivery_date, address_id)
            VALUES (:userId, 1, 0, CURRENT_TIMESTAMP, 1, CURRENT_DATE, 1)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        template.update(sql, param, keyHolder, new String[]{"id"});
        return keyHolder.getKey().intValue();
    }

    /**
     * Orderに新しいOrderItemを追加します。
     *
     * @param orderId  注文ID
     * @param itemId   商品ID
     * @param quantity 数量
     * @param size     サイズ
     */
    public void addOrderItem(Integer orderId, Integer itemId, Integer quantity, String size) {
        String sql = """
            INSERT INTO OrderItems (order_id, item_id, quantity, size)
            VALUES (:orderId, :itemId, :quantity, :size)
            """;

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("orderId", orderId)
                .addValue("itemId", itemId)
                .addValue("quantity", quantity)
                .addValue("size", size);
        template.update(sql, param);
    }

    /**
     * 指定したOrderItemを削除します。
     *
     * @param orderItemId 注文アイテムID
     */
    public void deleteOrderItem(Integer orderItemId) {
        String sql = "DELETE FROM OrderItems WHERE id = :orderItemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
        template.update(sql, param);
    
    }

     /* 注文情報の更新を行います.
     * paymentMethodが1だったらstatusを1に、paymentMethodが2だったらstatusを2にする
     *
     */
    public void update(Order order) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(order);
        String sql = "UPDATE orders SET " +
                "status_id = :statusId, " + // TODO paymentMethodIdとstatusIdは同じ値になる→支払方法増やしたらまずいかも
                "order_date = CURRENT_TIMESTAMP, " + // 今の日時
                "payment_method_id = :paymentMethodId, " +
                "delivery_date = :deliveryDate " +
                "WHERE id = :id";

        template.update(sql, param);
    }
}
