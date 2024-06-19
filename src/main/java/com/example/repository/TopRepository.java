package com.example.repository;

import com.example.domain.Item;
import com.example.domain.Top;
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
    CREATE TABLE Tops (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    price INTEGER NOT NULL CHECK (price >= 0),
    image_path TEXT
    );
     */

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Topオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Top> TOP_ROW_MAPPER = (rs, i) -> {
        Top top = new Top();
        top.setId(rs.getInt("id"));
        top.setName(rs.getString("name"));
        top.setDescription(rs.getString("description"));
        top.setPrice(rs.getInt("price"));
        top.setImagePath(rs.getString("image_path"));
        return top;
    };


    /**
     * Topの一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Top> findAll() {
        String sql = "SELECT id,name,description,price,image_path FROM tops ORDER BY id;";
        List<Top> topList = template.query(sql,TOP_ROW_MAPPER);
        return topList;
    }




}