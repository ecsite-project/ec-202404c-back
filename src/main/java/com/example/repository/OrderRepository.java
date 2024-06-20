package com.example.repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private final NamedParameterJdbcTemplate template;

    public OrderRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

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
                    order.setAddressId(rs.getInt("address_id"));
                    order.setItemList(new ArrayList<>());
                }

                int orderItemId = rs.getInt("order_item_id");
                if (!orderItemMap.containsKey(orderItemId)) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(orderItemId);
                    orderItem.setOrderId(rs.getInt("order_id"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItem.setSize(rs.getString("size").charAt(0));

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
}
