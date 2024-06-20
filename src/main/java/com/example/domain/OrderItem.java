package com.example.domain;

public class OrderItem {

    /**
     * 注文アイテムのID。
     */
    private Integer id;

    /**
     * 注文のID。
     */
    private Integer orderId;

    /**
     * 商品。
     */
    private Item item;

    /**
     * 数量。
     */
    private Integer quantity;

    /**
     * サイズ。
     */
    private char size;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", item=" + item +
                ", quantity=" + quantity +
                ", size=" + size +
                '}';
    }
}