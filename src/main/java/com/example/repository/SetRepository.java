package com.example.repository;


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
        item_id  INTEGER NOT NULL REFERENCES Items(id),
        top_id INTEGER NOT NULL REFERENCES Items(id),
        bottom_id INTEGER NOT NULL REFERENCES Items(id)
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
        set.setItemId(rs.getLong("item_id"));
        set.setTopId(rs.getLong("top_id"));
        set.setBottomId(rs.getLong("bottom_id"));
        return set;
    };


    /**
     * Setの一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Set> findAll() {
        String sql = """
            SELECT id, item_id, top_id, bottom_id 
            FROM Sets 
            ORDER BY id;
            """;
        return template.query(sql, SET_ROW_MAPPER);
    }
}








