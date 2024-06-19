package com.example.repository;


import com.example.domain.Bottom;
import com.example.domain.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SetRepository {

    /*
    CREATE TABLE Sets (
    id SERIAL PRIMARY KEY,
    top_id INTEGER NOT NULL REFERENCES Tops(id),
    bottom_id INTEGER NOT NULL REFERENCES Bottoms(id),
    name TEXT NOT NULL,
    description TEXT,
    price INTEGER NOT NULL CHECK (price >= 0),
    image_path TEXT
    );
     */

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Setオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Set> SET_ROW_MAPPER = (rs, i) -> {
        Set set = new Set();
        set.setId(rs.getInt("id"));
        set.setTopId(rs.getInt("top_id"));
        set.setBottomId(rs.getInt("bottom_id"));
        set.setName(rs.getString("name"));
        set.setDescription(rs.getString("description"));
        set.setPrice(rs.getInt("price"));
        set.setImagePath(rs.getString("image_path"));
        return set;
    };


    /**
     * Setの一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Set> findAll() {
        String sql = "SELECT id,name,top_id,bottom_id,description,price,image_path FROM Sets ORDER BY id;";
        List<Set> SetList = template.query(sql, SET_ROW_MAPPER);
        return SetList;
    }
}








