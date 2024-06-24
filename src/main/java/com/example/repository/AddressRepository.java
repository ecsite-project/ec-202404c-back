package com.example.repository;

import com.example.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * 住所（Address）に関するデータアクセスを行うリポジトリクラスです.
 *
 * @author io.yamanaka
 */
@Repository
public class AddressRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 注文の宛先情報の更新を行います.
     *
     */
    public Integer insert(Address address) {

        String sql = "INSERT INTO addresses (user_id, zipcode, prefecture, municipalities, address, telephone) VALUES ( " +
                ":userId, " +
                ":zipcode, " +
                ":prefecture, " +
                ":municipalities, " +
                ":address, " +
                ":telephone" +
                ")";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new BeanPropertySqlParameterSource(address);
        template.update(sql, param, keyHolder, new String[]{"id"});
        return keyHolder.getKey().intValue();
    }
}

