package com.example.repository;

import com.example.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * itemsテーブルを操作するリポジトリです.
 *
 * @author haruka.yamaneki
 */

@Repository
public class ItemRepository {

    /*
    drop table if exists items cascade;

    CREATE TABLE Items (
        id SERIAL PRIMARY KEY,
        name TEXT NOT NULL,
        description TEXT,
        price INTEGER NOT NULL CHECK (price >= 0),
        item_type VARCHAR(50) NOT NULL CHECK (item_type IN ('top', 'bottom', 'set')),
    image_path TEXT
    );


     */

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getInt("price"));
        item.setItemType(rs.getString("item_type"));
        item.setImagePath(rs.getString("image_path"));
        return item;
    };

    /**
     * 商品一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Item> findAll() {
        String sql = """
            SELECT id, name, description, price, item_type, image_path 
            FROM items 
            ORDER BY id;
            """;
        return template.query(sql, ITEM_ROW_MAPPER);
    }

    /**
     * 名前から商品を(曖昧)検索します.
     *
     * @param name 商品名
     * @return 検索された商品の情報一覧
     */
    public List<Item> searchByNameContaining(String name, String itemType) {
        String sql = """
            SELECT id, name, description, price, item_type, image_path 
            FROM items 
            WHERE name LIKE :name 
            AND
            item_type = :itemType 
            ORDER BY id;
            """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%").addValue("itemType", itemType);
        return template.query(sql, param, ITEM_ROW_MAPPER);
    }

    /**
     * 商品タイプから商品を検索します.
     *
     * @param itemType 商品タイプ
     * @return 検索された商品の情報一覧
     */
    public List<Item> findByType(String itemType) {
        String sql = """
            SELECT id, name, description, price, item_type, image_path 
            FROM items 
            WHERE item_type = :itemType 
            ORDER BY id;
            """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemType", itemType);
        return template.query(sql, param, ITEM_ROW_MAPPER);
    }

}
