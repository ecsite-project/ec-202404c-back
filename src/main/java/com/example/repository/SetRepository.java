package com.example.repository;


import com.example.domain.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Setsテーブルを操作するリポジトリです.
 *
 * @author io.yamanaka
 */
@Repository
public class SetRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Setオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Set> SET_ROW_MAPPER = (rs, i) -> {
        Set set = new Set();
        set.setId(rs.getInt("id"));
        set.setItemId(rs.getInt("item_id"));
        set.setTopId(rs.getInt("top_id"));
        set.setBottomId(rs.getInt("bottom_id"));
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








