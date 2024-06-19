package com.example.response;

import java.util.*;

public class ItemTypeResponse {
    private List<Object> items;

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemTypeResponse{" +
                "items=" + items +
                '}';
    }
}