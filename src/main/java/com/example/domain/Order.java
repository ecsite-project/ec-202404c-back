package com.example.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Order {

    /**
     * 注文のID。
     */
    private Integer id;

    /**
     * ユーザーのID。
     */
    private Integer userId;

    /**
     * 注文ステータスのID。
     */
    private Integer statusId;

    /**
     * 合計金額。
     */
    private Integer totalPrice;

    /**
     * 注文日。
     */
    private java.sql.Timestamp orderDate;

    /**
     * 支払い方法のID。
     */
    private Integer paymentMethodId;

    /**
     * 配送日。
     */
    private java.sql.Date deliveryDate;

    /**
     * 住所のID。
     */
    private Integer addressId;

    /**
     * 各注文商品のリスト1
     */
    private List<OrderItem> itemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", statusId=" + statusId +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", paymentMethodId=" + paymentMethodId +
                ", deliveryDate=" + deliveryDate +
                ", addressId=" + addressId +
                ", itemList=" + itemList +
                '}';
    }
}