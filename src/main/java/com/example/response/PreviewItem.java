package com.example.response;

public class PreviewItem {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String baseType;
    private String imagePath;
    private Integer topId;
    private String topImagePath;
    private Integer bottomId;
    private String bottomImagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String itemType) {
        this.baseType = itemType;
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
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemType='" + baseType + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", topId=" + topId +
                ", topImagePath='" + topImagePath + '\'' +
                ", bottomImagePath='" + bottomImagePath + '\'' +
                '}';
    }
}
