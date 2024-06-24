package com.example.request;

public class UserIdRequest {
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
