package com.example.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 注文情報を表すドメイン.
 *
 * @author io.yamanaka
 *
 */
public class Order {
    private Integer id;
    private Integer userId;
    private Integer status;
    private Integer totalPrice;
    private Date orderDate;
    private String destinationName;
    private String destinationEmail;
    private String destinationZipcode;
    private String destinationPrefecture;
    private String destinationMunicipalities;
    private String destinationAddress;
    private String destinationTel;
    private Timestamp deliveryTime;
    private Integer paymentMethod;
    private List<OrderItem> orderItemList;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", destinationName='" + destinationName + '\'' +
                ", destinationEmail='" + destinationEmail + '\'' +
                ", destinationZipcode='" + destinationZipcode + '\'' +
                ", destinationPrefecture='" + destinationPrefecture + '\'' +
                ", destinationMunicipalities='" + destinationMunicipalities + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", destinationTel='" + destinationTel + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", paymentMethod=" + paymentMethod +
                ", orderItemList=" + orderItemList +
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public String getDestinationZipcode() {
        return destinationZipcode;
    }

    public void setDestinationZipcode(String destinationZipcode) {
        this.destinationZipcode = destinationZipcode;
    }

    public String getDestinationPrefecture() {
        return destinationPrefecture;
    }

    public void setDestinationPrefecture(String destinationPrefecture) {
        this.destinationPrefecture = destinationPrefecture;
    }

    public String getDestinationMunicipalities() {
        return destinationMunicipalities;
    }

    public void setDestinationMunicipalities(String destinationMunicipalities) {
        this.destinationMunicipalities = destinationMunicipalities;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationTel() {
        return destinationTel;
    }

    public void setDestinationTel(String destinationTel) {
        this.destinationTel = destinationTel;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}