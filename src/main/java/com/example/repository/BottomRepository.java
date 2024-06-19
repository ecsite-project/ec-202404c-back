package com.example.repository;

import com.example.domain.Bottom;
import com.example.domain.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class BottomRepository {
    /*
    CREATE TABLE Bottoms (
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
     * Bottomオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Bottom> BOTTOM_ROW_MAPPER = (rs, i) -> {
        Bottom bottom = new Bottom();
        bottom.setId(rs.getInt("id"));
        bottom.setName(rs.getString("name"));
        bottom.setDescription(rs.getString("description"));
        bottom.setPrice(rs.getInt("price"));
        bottom.setImagePath(rs.getString("image_path"));
        return bottom;
    };


    /**
     * Bottomの一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Bottom> findAll() {
        String sql = "SELECT id,name,description,price,image_path FROM Bottoms ORDER BY id;";
        List<Bottom> BottomList = template.query(sql, BOTTOM_ROW_MAPPER);
        return BottomList;
    }
}




