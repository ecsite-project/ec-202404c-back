package com.example.request;

/**
 * ユーザ登録リクエストを表すクラスです.
 *
 * @author io.yamanaka
 */
public class RegisterUserRequest {

    /** ユーザの名前 */
    private String name;

    /** ユーザのメールアドレス */
    private String email;

    /** ユーザのパスワード */
    private String password;

    /** ユーザの郵便番号 */
    private String zipcode;

    /** ユーザの都道府県 */
    private String prefecture;

    /** ユーザの市区町村 */
    private String municipalities;

    /** ユーザの住所 */
    private String address;

    /** ユーザの電話番号 */
    private String telephone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    @Override
    public String toString() {
        return "RegisterUserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", municipalities='" + municipalities + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
