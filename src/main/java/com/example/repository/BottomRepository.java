package com.example.repository;

import com.example.domain.Bottom;
import com.example.domain.Top;
import com.example.service.ShowItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class BottomRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;


    /*
    drop table if exists bottoms cascade;
    create table bottoms(
        name text not null
      , description text not null
      , price integer not null
      , image_path text not null
      , deleted boolean not null
    ) ;
     */

    /**
     * Bottomオブジェクトを生成するローマッパー.
     */
    /**
     * Bottomオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Bottom> BOTTOM_ROW_MAPPER = (rs, i) -> {
        Bottom bottom = new Bottom();
        bottom.setName(rs.getString("name"));
        bottom.setDescription(rs.getString("description"));
        bottom.setPrice(rs.getInt("price"));
        bottom.setImagePath(rs.getString("image_path"));
        bottom.setDeleted(rs.getBoolean("deleted"));
        return bottom;
    };

    /**
     * Bottomの一覧表示を行います.
     *
     * @return 商品一覧
     */
    public List<Object> findAll() {
        String sql = "SELECT id,name,description,price,image_path,deleted FROM bottoms ORDER BY id;";
        List<Bottom> topList = template.query(sql,BOTTOM_ROW_MAPPER);
        return Collections.singletonList(topList);
    }



}
