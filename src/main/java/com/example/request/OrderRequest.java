package com.example.request;

import java.sql.Date;

/**
 * OrderRequestは、注文を行うための詳細情報を表します。
 *
 * @author io.yamanaka
 */
public class OrderRequest {

    /**
     * ID。
     */
    private Integer id;

    /**
     * 注文ID。
     */
    private Integer orderId;

    /**
     * ユーザーID。
     */
    private Integer userId;

    /**
     * 注文の支払い方法のID。
     */
    private Integer paymentMethodId;

    /**
     * 配達日。
     */
    private java.sql.Date deliveryDate;

    /**
     * 配達先の名前。
     */
    private String destinationName;

    /**
     * 配達先のメールアドレス。
     */
    private String destinationEmail;

    /**
     * 配達先の郵便番号。
     */
    private String zipcode;

    /**
     * 配達先の都道府県。
     */
    private String prefecture;

    /**
     * 配達先の市町村。
     */
    private String municipalities;

    /**
     * 配達先の住所。
     */
    private String address;

    /**
     * 配達先の電話番号。
     */
    private String telephone;

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", paymentMethodId=" + paymentMethodId +
                ", deliveryDate=" + deliveryDate +
                ", destinationName='" + destinationName + '\'' +
                ", destinationEmail='" + destinationEmail + '\'' +
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
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