package com.example.repository;

import com.example.domain.Order;
import com.example.domain.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;


    //TODO statusの変更はReactでやるかも
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
