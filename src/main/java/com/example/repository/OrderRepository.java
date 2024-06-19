package com.example.repository;

import com.example.domain.OrderItem;
import com.example.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ordersテーブルを操作するリポジトリです.
 *
 * @author io.yamanaka
 */
@Repository
public class OrderRepository {

    /*
    drop table if exists items cascade;

    create table orders (
      id integer primary key
      ,userId integer not null
      ,status integer not null
      , total_price integer not null
      //, order_date integer not null
      , destination_name text not null
      , destination_email text not null
      //, destination_zipcode text not null
      , destination_prefecture text not null
      , destination_municipalities text not null
      , destination_address text not null
      , destination_tel text not null
      //, delivery_time integer not null
      , payment_method integer not null
      , order_item_list text not null
    ) ;

     */

    @Autowired
    private NamedParameterJdbcTemplate template;

    // Extractorを使用
    private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
        Map<Integer, Order> orderMap = new LinkedHashMap<>();

        while (rs.next()) {
            int orderId = rs.getInt("order_id");
            Order order = orderMap.get(orderId);

            if (order == null) {
                order = new Order();
                order.setId(orderId);
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getInt("status"));
                order.setTotalPrice(rs.getInt("total_price"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setDestinationName(rs.getString("destination_name"));
                order.setDestinationEmail(rs.getString("destination_email"));
                order.setDestinationZipcode(rs.getString("destination_zipcode"));
                order.setDestinationPrefecture(rs.getString("destination_prefecture"));
                order.setDestinationMunicipalities(rs.getString("destination_municipalities"));
                order.setDestinationAddress(rs.getString("destination_address"));
                order.setDestinationTel(rs.getString("destination_tel"));
                order.setDeliveryTime(rs.getTimestamp("delivery_time"));
                order.setPaymentMethod(rs.getInt("payment_method"));
                order.setOrderItemList(new ArrayList<>());
                orderMap.put(orderId, order);
            }

            int orderItemId = rs.getInt("order_item_id");
            if (orderItemId > 0) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(orderItemId);
                orderItem.setItemId(rs.getInt("item_id"));
                orderItem.setOrderId(rs.getInt("order_item_order_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setItemType(rs.getString("item_type"));
                orderItem.setSize(rs.getString("size").charAt(0));
                order.getOrderItemList().add(orderItem);
            }
        }

        return new ArrayList<>(orderMap.values());
    };


    /**
     * 注文を1件表示します.
     *
     * @return 注文情報
     */
    public List<Order> load(Integer orderId) {
        String sql = "SELECT " +
                "o.id AS order_id, " +
                "o.user_id, " +
                "o.status, " +
                "o.total_price, " +
                "o.order_date, " +
                "o.destination_name, " +
                "o.destination_email, " +
                "o.destination_zipcode, " +
                "o.destination_prefecture, " +
                "o.destination_municipalities, " +
                "o.destination_address, " +
                "o.destination_tel, " +
                "o.delivery_time, " +
                "o.payment_method, " +
                "oi.id AS order_item_id, " +
                "oi.item_id, " +
                "oi.order_id AS order_item_order_id, " +
                "oi.quantity, " +
                "oi.item_type, " +
                "oi.size " +
                "FROM orders o " +
                "LEFT JOIN order_items oi ON o.id = oi.order_id " +
                "WHERE o.id = :orderId";
        ;
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
        List<Order> orderList = template.query(sql,param,ORDER_RESULT_SET_EXTRACTOR);
        return orderList;
    }

}
