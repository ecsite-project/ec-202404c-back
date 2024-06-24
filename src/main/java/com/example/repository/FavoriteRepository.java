package com.example.repository;

import com.example.domain.Item;
import com.example.response.ItemTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Favoriteリポジトリクラス。
 * ユーザのお気に入り登録、解除、一覧取得を管理する。
 */
@Repository
public class FavoriteRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * お気に入りを追加する。
     *
     * @param userId ユーザID
     * @param itemId 商品ID
     */
    public void addFavorite(Integer userId, Integer itemId) {
        String sql = "INSERT INTO Favorite (user_id, item_id) VALUES (:userId, :itemId)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("itemId", itemId);
        template.update(sql, param);
    }

    /**
     * お気に入りを解除する。
     *
     * @param userId ユーザID
     * @param itemId 商品ID
     */
    public void removeFavorite(Integer userId, Integer itemId) {
        String sql = "DELETE FROM Favorite WHERE user_id = :userId AND item_id = :itemId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("itemId", itemId);
        template.update(sql, param);
    }

    /**
     * 指定したユーザIDのお気に入り商品一覧を取得する。
     *
     * @param userId ユーザID
     * @return お気に入り商品一覧
     */
    public ItemTypeResponse getFavoritesByUserId(Integer userId) {
        String sql = """
            SELECT 
                i.id, 
                i.name, 
                i.description, 
                i.price, 
                i.item_type, 
                i.image_path
            FROM 
                Items AS i
            INNER JOIN 
                Favorite AS f ON i.id = f.item_id
            WHERE 
                f.user_id = :userId
        """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        List<Item> items = template.query(sql, param, ITEM_ROW_MAPPER);
        ItemTypeResponse response = new ItemTypeResponse();
        response.setItems(items);
        return response;
    }

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
}
