package com.example.response;

import java.util.List;

public class PreviewItemResponse {

    private List<PreviewItem> items;

    public List<PreviewItem> getItems() {
        return items;
    }

    public void setItems(List<PreviewItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PreviewItemResponse{" +
                "items=" + items +
                '}';
    }
}
