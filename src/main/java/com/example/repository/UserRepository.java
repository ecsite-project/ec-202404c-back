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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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

    /*
     create table users (
     id serial primary key
     , name varchar(100) not null
     , email varchar(100) not null unique
     , password text not null
     , zipcode varchar(8) not null
     , prefecture varchar(10) not null
     , municipalities varchar(10) not null
     , address varchar(20) not null
     , telephone varchar(15) not null
    ) ;
     */

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
        String sql = "INSERT INTO users(id,name,email,password,zipcode,prefecture,municipalities,address,telephone)values(:id,:name,:email,:password,:zipcode,:prefecture,:municipalities,:address,:telephone);";
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得します.
     * 一件も存在しない場合にはnullを返します。
     *
     * @param email メールアドレス
     * @param password    パスワード
     * @return 管理者情報
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






}
