package com.example.repository;

import com.example.domain.Order;
import com.example.domain.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
        Order order = new Order();
        order.setId(rs.getInt("id"));
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
        return order;


    };

    /**
     * 注文情報の更新を行います.
     * paymentMethodが1だったらstatusを1に、paymentMethodが2だったらstatusを2にする
     *
     */
    public void update(Order order) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(order);
        String sql = "UPDATE orders SET " +
                "user_id = :userId, " +
                "status = :status, " +
                "total_price = :totalPrice, " +
                "order_date = :orderDate, " +
                "destination_name = :destinationName, " +
                "destination_email = :destinationEmail, " +
                "destination_zipcode = :destinationZipcode, " +
                "destination_prefecture = :destinationPrefecture, " +
                "destination_municipalities = :destinationMunicipalities, " +
                "destination_address = :destinationAddress, " +
                "destination_tel = :destinationTel, " +
                "delivery_time = :deliveryTime, " +
                "payment_method = :paymentMethod " +
                "WHERE id = :id";
        template.update(sql, param);
    }
}
