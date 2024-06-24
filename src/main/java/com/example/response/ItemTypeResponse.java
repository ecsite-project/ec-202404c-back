package com.example.response;

import com.example.domain.Item;

import java.util.*;

/**
 * 商品タイプを返すレスポンスクラスです.
 *
 * @author io.yamanaka
 */
public class ItemTypeResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemTypeResponse{" +
                "items=" + items +
                '}';
    }
}