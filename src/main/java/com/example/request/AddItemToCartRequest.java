package com.example.request;

public class AddItemToCartRequest {

    private Integer userId;
    private Integer itemId;
    private String itemType;
    private Integer quantity;
    private String size;

    // ゲッターとセッター

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "AddItemToCartRequest{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", quantity=" + quantity +
                ", size=" + size +
                '}';
    }
}
