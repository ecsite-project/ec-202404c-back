package com.example.domain;

/**
 * 宛先住所情報のドメイン.
 *
 * @author io.yamanaka
 */
public class Address {
    /**
     * ID。
     */
    private Integer id;
    /**
     * ユーザID。
     */
    private Integer userId;
    /**
     * 郵便番号。
     */
    private String zipcode;
    /**
     * 都道府県。
     */
    private String prefecture;
    /**
     * 市区町村名。
     */
    private String municipalities;
    /**
     * 住所。
     */
    private String address;
    /**
     * 電話番号。
     */
    private String telephone;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", zipcode='" + zipcode + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", municipalities='" + municipalities + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(String municipalities) {
        this.municipalities = municipalities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
