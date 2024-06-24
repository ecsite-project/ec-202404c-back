package com.example.request;

/**
 * ログイン用のリクエストを表すクラスです.
 *
 * @author io.yamanaka
 */
public class LoginRequest {
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


