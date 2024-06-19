package com.example.domain;

import java.util.Date;

/**
 * 注文商品情報を表すドメイン.
 *
 * @author io.yamanaka
 *
 */
public class OrderItem {
    private Integer id;
    private Integer itemId;
    private Integer orderId;
    private Integer quantity;
    private Item item;
    private String itemType ;
    private Character size;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", item=" + item +
                ", itemType='" + itemType + '\'' +
                ", size=" + size +
                '}';
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String item_type) {
        this.itemType = item_type;
    }

    public Character getSize() {
        return size;
    }

    public void setSize(Character size) {
        this.size = size;
    }
}
