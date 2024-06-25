package com.example.request;

/**
 * お気に入りの追加と削除のリクエストクラス。
 */
public class FavoriteRequest {

    private Integer userId;
    private Integer itemId;

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

    @Override
    public String toString() {
        return "FavoriteRequest{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                '}';
    }
}
