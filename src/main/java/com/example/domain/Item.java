package com.example.domain;

/**
 * 商品を表すエンティティクラス。
 */
public class Item {

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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemType='" + itemType + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

