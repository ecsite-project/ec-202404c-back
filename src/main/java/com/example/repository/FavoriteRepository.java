package com.example.repository;

import com.example.domain.Item;
import com.example.response.ItemTypeResponse;
import com.example.response.PreviewItem;
import com.example.response.PreviewItemResponse;
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

    private static final RowMapper<PreviewItem> PREVIEW_ITEM_RESPONSE_ROW_MAPPER = (rs, i) -> {
        PreviewItem item = new PreviewItem();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getInt("price"));
        item.setBaseType(rs.getString("item_type"));
        item.setImagePath(rs.getString("image_path"));
        item.setTopId(rs.getInt("top_id"));
        item.setTopImagePath(rs.getString("top_image_path"));
        item.setBottomId(rs.getInt("bottom_id"));
        item.setBottomImagePath(rs.getString("bottom_image_path"));
        return item;
    };

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

    public List<PreviewItem> getFavoritesByUserIdForPreview(Integer userId) {
        String sql = """
        WITH FavoriteSets AS (
            SELECT 
                i.id as  item_id, 
                i.name as name, 
                i.description as description, 
                i.price as price, 
                i.item_type as item_type, 
                i.image_path as image_path,
                0 AS top_id,
                NULL AS top_image_path,
                0 AS bottom_id,
                NULL AS bottom_image_path
            FROM 
                Items AS i
            INNER JOIN 
                Favorite AS f ON i.id = f.item_id
            WHERE 
                f.user_id = :userId AND i.item_type = 'set'
        ),
        FavoriteItems AS (
            SELECT 
                i.id as item_id, 
                i.name as name, 
                i.description as description, 
                i.price as price, 
                i.item_type as item_type, 
                i.image_path as image_path
            FROM 
                Items AS i
            INNER JOIN 
                Favorite AS f ON i.id = f.item_id
            WHERE 
                f.user_id = :userId AND i.item_type IN ('top', 'bottom')
        ),
        RelatedSets AS (
            SELECT DISTINCT
                si.id AS item_id,
                si.name as name, 
                si.description as description, 
                si.price as price, 
                CASE WHEN s.top_id IN (SELECT item_id FROM FavoriteItems WHERE item_type = 'top') THEN 'top' WHEN s.bottom_id IN (SELECT item_id FROM FavoriteItems WHERE item_type = 'bottom') THEN 'bottom' ELSE 'set' END AS item_type,
                si.image_path as image_path,
                s.top_id AS top_id,
                (SELECT i.image_path FROM Items AS i WHERE i.id = s.top_id) AS top_image_path,
                s.bottom_id AS bottom_id,
                (SELECT i.image_path FROM Items AS i WHERE i.id = s.bottom_id) AS bottom_image_path
            FROM 
                Sets AS s
            INNER JOIN 
                Items AS si ON s.item_id = si.id
            WHERE 
                (s.top_id IN (SELECT item_id FROM FavoriteItems WHERE item_type = 'top')
                OR 
                s.bottom_id IN (SELECT item_id FROM FavoriteItems WHERE item_type = 'bottom'))
                AND si.id NOT IN (SELECT item_id FROM FavoriteSets)
        )
        SELECT * FROM FavoriteSets
        UNION ALL
        SELECT * FROM RelatedSets
    """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        List<PreviewItem> items = template.query(sql, param, PREVIEW_ITEM_RESPONSE_ROW_MAPPER);
        return items;
    }



}
