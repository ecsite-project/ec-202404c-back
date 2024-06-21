package com.example.request;

public class DeleteItemFromCartRequest {

    private Integer orderItemId;

    private Integer userId;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DeleteItemFromCartRequest{" +
                "orderItemId=" + orderItemId +
                ", userId=" + userId +
                '}';
    }
}
