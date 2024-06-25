package com.example.response;

public class PreviewItem {
    private Integer itemId;
    private String name;
    private String description;
    private Integer price;
    private String itemType;
    private String imagePath;
    private Integer topId;
    private String topImagePath;
    private Integer bottomId;
    private String bottomImagePath;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public String getTopImagePath() {
        return topImagePath;
    }

    public void setTopImagePath(String topImagePath) {
        this.topImagePath = topImagePath;
    }

    public Integer getBottomId() {
        return bottomId;
    }

    public void setBottomId(Integer bottomId) {
        this.bottomId = bottomId;
    }

    public String getBottomImagePath() {
        return bottomImagePath;
    }

    public void setBottomImagePath(String bottomImagePath) {
        this.bottomImagePath = bottomImagePath;
    }

    @Override
    public String toString() {
        return "PreviewItemResponse{" +
                "bottomId=" + bottomId +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemType='" + itemType + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", topId=" + topId +
                ", topImagePath='" + topImagePath + '\'' +
                ", bottomImagePath='" + bottomImagePath + '\'' +
                '}';
    }
}
