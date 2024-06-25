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
     * 名前と商品タイプから商品を(曖昧)検索します.
     *
     * @param name 商品名
     * @param itemType 商品タイプ (nullの場合は商品タイプでの絞り込みを行いません)
     * @return 検索された商品の情報一覧
     */
    public List<Item> searchByNameAndTypeContaining(String itemType, String name) {
        String sql = """
        SELECT id, name, description, price, item_type, image_path 
        FROM items 
        WHERE name LIKE :name 
        """ + (itemType != null ? " AND item_type = :itemType " : "") + """
        
        ORDER BY id;
        """;

        MapSqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
        if (itemType != null) {
            param.addValue("itemType", itemType);
        }

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
                s.bottom_id 
            FROM 
                Items as i
            LEFT OUTER JOIN 
                Sets as s 
            ON 
                i.id = s.item_id
            WHERE 
                i.id = :id
            """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", itemId);
        return template.queryForObject(sql, param, ITEM_DETAIL_RESPONSE_ROW_MAPPER);
    }
}
