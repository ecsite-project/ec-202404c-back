package com.example.request;

/**
 * ユーザIDリクエストを表すクラスです.
 *
 * @author io.yamanaka
 */
public class UserIdRequest {
    /** ユーザID */
    private Integer userId;

    @Override
    public String toString() {
        return "UserIdRequest{" +
                "userId=" + userId +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
