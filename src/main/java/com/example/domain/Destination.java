package com.example.domain;

/**
 * 宛先情報のドメイン.
 *
 * @author io.yamanaka
 */
public class Destination {
    /**
     * 注文ID。
     */
    private Integer orderId;
    /**
     * 宛先の名前。
     */
    private String destinationName;
    /**
     * 宛先のメールアドレス。
     */
    private String destinationEmail;
    /**
     * 宛先の住所ID。
     */
    private Integer addressId;

    @Override
    public String toString() {
        return "Destination{" +
                "orderId=" + orderId +
                ", destinationName='" + destinationName + '\'' +
                ", destinationEmail='" + destinationEmail + '\'' +
                ", addressId=" + addressId +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
