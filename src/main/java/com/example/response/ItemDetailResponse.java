package com.example.response;

public class ItemDetailResponse {

    /**
     * 商品のID。
     */
    private Integer id;

    /**
     * 商品の名前。
     */
    private String name;

    /**
     * 商品の説明。
     */
    private String description;

    /**
     * 商品の価格。
     */
    private Integer price;

    /**
     * 商品の種類。 ('top', 'bottom', 'set')
     */
    private String itemType;

    /**
     * 商品の画像パス。
     */
    private String imagePath;

    /**
     * セットのトップアイテムのID。
     */
    private Integer topId;

    /**
     * セットのボトムアイテムのID。
     */
    private Integer bottomId;

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

    public Integer getBottomId() {
        return bottomId;
    }

    public void setBottomId(Integer bottomId) {
        this.bottomId = bottomId;
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemType='" + itemType + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", topId=" + topId +
                ", bottomId=" + bottomId +
                '}';
    }
}
