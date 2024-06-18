package com.example.repository;

import com.example.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    create table items (
      id integer primary key
      ,top_id integer not null
      ,bottom_id integer not null
      , name text not null
      , description text not null
      , price integer not null
      , image_path text not null
    ) ;

     */

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setId(rs.getInt("top_id"));
        item.setId(rs.getInt("bottom_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getInt("price"));
        item.setImagePath(rs.getString("imagePath"));
        return item;


    };

    /**
     * 商品一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Item> findAll() {
        String sql = "SELECT id,top_id,bottom_id,name,description,price,imagePath FROM items ORDER BY id;";
        List<Item> itemList = template.query(sql,ITEM_ROW_MAPPER);
        return itemList;
    }

}
