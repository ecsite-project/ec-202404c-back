package com.example.request;

/**
 * カートから商品を削除するためのリクエストを表すクラスです.
 *
 * @author io.yamanaka
 */
public class DeleteItemFromCartRequest {
    /**　注文アイテムID */
    private Integer orderItemId;
    /**　ユーザID */
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
