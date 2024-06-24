package com.example.repository;

import com.example.domain.Item;
import com.example.response.ItemDetailResponse;
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

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Itemオブジェクトを生成するローマッパー.
     */
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
     * ItemDetailResponseオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<ItemDetailResponse> ITEM_DETAIL_RESPONSE_ROW_MAPPER = (rs, i) -> {
        ItemDetailResponse itemDetail = new ItemDetailResponse();
        itemDetail.setId(rs.getInt("id"));
        itemDetail.setName(rs.getString("name"));
        itemDetail.setDescription(rs.getString("description"));
        itemDetail.setPrice(rs.getInt("price"));
        itemDetail.setItemType(rs.getString("item_type"));
        itemDetail.setImagePath(rs.getString("image_path"));
        itemDetail.setTopId(rs.getInt("top_id"));
        itemDetail.setBottomId(rs.getInt("bottom_id"));
        itemDetail.setTopImagePath(rs.getString("top_image_path"));
        itemDetail.setBottomImagePath(rs.getString("bottom_image_path"));
        itemDetail.setFavorite(false);
        return itemDetail;
    };

    /**
     * ItemDetailResponseオブジェクトを生成するローマッパー(お気に入り登録機能対応).
     */
    private static final RowMapper<ItemDetailResponse> ITEM_DETAIL_RESPONSE_ROW_MAPPER_WITH_FAVORITE = (rs, i) -> {
        ItemDetailResponse itemDetail = new ItemDetailResponse();
        itemDetail.setId(rs.getInt("id"));
        itemDetail.setName(rs.getString("name"));
        itemDetail.setDescription(rs.getString("description"));
        itemDetail.setPrice(rs.getInt("price"));
        itemDetail.setItemType(rs.getString("item_type"));
        itemDetail.setImagePath(rs.getString("image_path"));
        itemDetail.setTopId(rs.getInt("top_id"));
        itemDetail.setBottomId(rs.getInt("bottom_id"));
        itemDetail.setTopImagePath(rs.getString("top_image_path"));
        itemDetail.setBottomImagePath(rs.getString("bottom_image_path"));
        itemDetail.setFavorite(rs.getBoolean("is_favorite"));
        return itemDetail;
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
    public List<Item> searchByNameContaining(String name) {
        String sql = """
            SELECT id, name, description, price, item_type, image_path 
            FROM items 
            WHERE name LIKE :name 
            ORDER BY id;
            """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
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

    /**
     * 指定したアイテムIDに対応するItemDetailResponseオブジェクトを取得します。
     *
     * @param itemId 検索するItem
     * @return 検索結果
     */
    public ItemDetailResponse findById(Integer itemId) {
        String sql = """
            SELECT 
                i.id, 
                i.name, 
                i.description, 
                i.price, 
                i.item_type, 
                i.image_path,
                s.top_id,
                s.bottom_id,
                top_item.image_path AS top_image_path,
                bottom_item.image_path AS bottom_image_path
            FROM 
                Items AS i
            LEFT OUTER JOIN 
                Sets AS s ON i.id = s.item_id
            LEFT OUTER JOIN 
                Items AS top_item ON s.top_id = top_item.id
            LEFT OUTER JOIN 
                Items AS bottom_item ON s.bottom_id = bottom_item.id
            WHERE 
                i.id = :id
        """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", itemId);
        return template.queryForObject(sql, param, ITEM_DETAIL_RESPONSE_ROW_MAPPER);
    }

    /**
     * 指定したItemIDに対応し，且つログイン中のユーザのお気に入り情報を含めたItemDetailResponseオブジェクトを取得します。
     *
     * @param itemId 検索するItemID
     * @param userId ログイン中のユーザ
     * @return 商品詳細
     */
    public ItemDetailResponse findByIdWithFavorite(Integer itemId, Integer userId) {
        String sql = """
        SELECT 
            i.id, 
            i.name, 
            i.description, 
            i.price, 
            i.item_type, 
            i.image_path,
            s.top_id,
            s.bottom_id,
            top_item.image_path AS top_image_path,
            bottom_item.image_path AS bottom_image_path,
            CASE WHEN f.item_id IS NOT NULL THEN TRUE ELSE FALSE END AS is_favorite
        FROM 
            Items AS i
        LEFT OUTER JOIN 
            Sets AS s ON i.id = s.item_id
        LEFT OUTER JOIN 
            Items AS top_item ON s.top_id = top_item.id
        LEFT OUTER JOIN 
            Items AS bottom_item ON s.bottom_id = bottom_item.id
        LEFT OUTER JOIN 
            Favorite AS f ON i.id = f.item_id AND f.user_id = :userId
        WHERE 
            i.id = :id
    """;

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", itemId)
                .addValue("userId", userId);
        return template.queryForObject(sql, param, ITEM_DETAIL_RESPONSE_ROW_MAPPER_WITH_FAVORITE);
    }
}
