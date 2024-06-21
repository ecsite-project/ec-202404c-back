package com.example.repository;

import com.example.domain.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 宛先（Destination）に関するデータアクセスを行うリポジトリクラスです.
 *
 * @author io.yamanaka
 */
@Repository
public class DestinationRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 注文の宛先情報の更新を行います.
     *
     */
    public void insert(Destination destination) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(destination);
        String sql = "INSERT INTO destinations (order_id, destination_name, destination_email, address_id) VALUES (" +
                ":orderId, " +
                ":destinationName, " +
                ":destinationEmail, " +
                ":orderId) "; // orderIdとaddressIdは1対1で同じ値になる
        template.update(sql, param);
    }
}
