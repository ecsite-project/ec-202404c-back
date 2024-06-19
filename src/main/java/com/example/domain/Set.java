package com.example.domain;

/**
 * セット商品を表すエンティティクラス。
 */
public class Set {

    /**
     * セット商品のID。
     */
    private Integer id;

    /**
     * セットに含まれるアイテムのID。
     */
    private Long itemId;

    /**
     * セットのトップアイテムのID。
     */
    private Long topId;

    /**
     * セットのボトムアイテムのID。
     */
    private Long bottomId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getTopId() {
        return topId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

    public Long getBottomId() {
        return bottomId;
    }

    public void setBottomId(Long bottomId) {
        this.bottomId = bottomId;
    }
}
