package com.example.repository;

import com.example.domain.Item;
import com.example.domain.Top;
import com.example.service.ShowItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class TopRepository {

    /*
     drop table if exists tops cascade;
    create table tops(
       id integer primary key
      , name text not null
      , description text not null
      , price integer not null
      , image_path text not null
      , deleted boolean not null
    ) ;
     */

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Topオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Top> TOP_ROW_MAPPER = (rs, i) -> {
        Top top = new Top();
        top.setName(rs.getString("name"));
        top.setDescription(rs.getString("description"));
        top.setPrice(rs.getInt("price"));
        top.setImagePath(rs.getString("image_path"));
        top.setDeleted(rs.getBoolean("deleted"));
        return top;
    };

    /**
     * Topの一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Object> findAll() {
        String sql = "SELECT id,name,description,price,image_path,deleted FROM tops ORDER BY id;";
        List<Top> topList = template.query(sql,TOP_ROW_MAPPER);
        return Collections.singletonList(topList);
    }

}
