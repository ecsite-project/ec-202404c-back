package com.example.request;

/**
 * 注文IDリクエストを表すクラスです.
 *
 * @author io.yamanaka
 */
public class OrderIdRequest {
    /** 注文ID */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}