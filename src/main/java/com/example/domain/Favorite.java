package com.example.domain;

/**
 * Favorite テーブルを操作するエンティティ.
 *
 * @author yamada
 */
public class Favorite {
    /**
     * 主キー
     */
    private Integer id;

    /**
     * 登録するユーザのid
     */
    private Integer userId;

    /**
     * 登録対象となる商品Id
     */
    private Integer itemId;
}
