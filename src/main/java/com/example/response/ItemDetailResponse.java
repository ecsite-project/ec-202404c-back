package com.example.response;

/**
 * 商品詳細を返すレスポンスクラスです.
 *
 * @author io.yamanaka
 */
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

    /**
     * セットのトップアイテムの画像パス。
     */
    private String topImagePath;

    /**
     * セットのボトムアイテムの画像パス。
     */
    private String bottomImagePath;

    /**
     * 現在登録しているユーザがお気に入り登録しているか。
     */
    private boolean isFavorite;

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

    public String getTopImagePath() {
        return topImagePath;
    }

    public void setTopImagePath(String topImagePath) {
        this.topImagePath = topImagePath;
    }

    public String getBottomImagePath() {
        return bottomImagePath;
    }

    public void setBottomImagePath(String bottomImagePath) {
        this.bottomImagePath = bottomImagePath;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "ItemDetailResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemType='" + itemType + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", topId=" + topId +
                ", bottomId=" + bottomId +
                ", topImagePath='" + topImagePath + '\'' +
                ", bottomImagePath='" + bottomImagePath + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
