package com.example.repository;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Userテーブルを操作するリポジトリです.
 *
 * @author haruka.yamaneki
 */
@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Userオブジェクトを作成するローマッパーです.
     */
    private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    /**
     * ユーザー登録を行います.
     *
     * @param user ユーザー情報
     */
    public void insert(User user) {
        String sql = "INSERT INTO users(name,email,password,zipcode,prefecture,municipalities,address,telephone)values(:name,:email,:password,:zipcode,:prefecture,:municipalities,:address,:telephone);";
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        template.update(sql, param);
    }


    /**
     * メールアドレスとパスワードからユーザ情報を取得します.
     * 一件も存在しない場合にはnullを返します。
     *
     * @param email メールアドレス
     * @param password    パスワード
     * @return ユーザ情報
     */
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT id,name,email,password,zipcode,prefecture,municipalities,address,telephone from users WHERE email=:email AND password =:password;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password",password);
        List<User> userList = template.query(sql, param, USER_ROW_MAPPER);

        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }


    /**
     * メールアドレスからユーザ情報を取得します.
     * 一件も存在しない場合にはnullを返します。
     *
     * @param email メールアドレス
     * @return ユーザ情報
     */
    public User findByEmail(String email) {
        String sql = "SELECT id,name,email,password,zipcode,prefecture,municipalities,address,telephone from users WHERE email=:email";
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        return template.queryForObject(sql, param, USER_ROW_MAPPER);
    }


    /**
     * 主キー検索をする.
     *
     * @param id ID
     * @return ユーザー情報
     */
    public User load(int id) {
        String sql = "SELECT id,name,email,password,zipcode,address,telephone FROM users WHERE id=:id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, param, USER_ROW_MAPPER);
    }


    /**
     * メールアドレスとパスワードからユーザーを取得する.
     *
     * @param email    取得したいユーザーのメールアドレス
     * @return ユーザーが存在すれば1つだけユーザードメインの入ったリスト、なければnull
     */
    public User findByMailAddress(String email) {
        String sql = "SELECT id,name,email,password,zipcode,address,telephone FROM users WHERE email=:email;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }
}
