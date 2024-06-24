package com.example.domain;

/**
 * セット商品を表すドメイン.
 *
 * @author io.yamanaka
 */
public class Set {

    /**
     * セット商品のID。
     */
    private Integer id;

    /**
     * セットに含まれるアイテムのID。
     */
    private Integer itemId;

    /**
     * セットのトップアイテムのID。
     */
    private Integer topId;

    /**
     * セットのボトムアイテムのID。
     */
    private Integer bottomId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public Integer getBottomId() {
        return bottomId;
    }

    public void setBottomId(Integer bottomId) {
        this.bottomId = bottomId;
    }
}
