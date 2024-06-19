package com.example.repository;

import com.example.domain.Item;
import com.example.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

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
      , order_date integer not null★
      , destination_name text not null
      , destination_email text not null
      , destination_zipcode text not null★
      , destination_prefecture text not null
      , destination_municipalities text not null
      , destination_address text not null
      , destination_tel text not null
      , delivery_time integer not null★
      , payment_method integer not null
      , order_item_list text not null
    ) ;

     */

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
     * 注文を1件表示します.
     *
     * @return 注文情報
     */
    public Order load(Integer orderId) {
        String sql = """
                SELECT id, user_id, status, total_price, order_date, destination_name,
                destination_email, destination_zipcode, destination_prefecture,
                destination_municipalities, destination_address, destination_tel,
                delivery_time, payment_method FROM orders WHERE id=:id;
                """
        ;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderId);
        Order order = template.query(sql,param,ORDER_ROW_MAPPER).get(0);
        return order;
    }

}
