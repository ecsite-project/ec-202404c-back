package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ログイン認証に関するサービス.
 *
 * @author io.yamanaka
 */
@Service
@Transactional
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * ログイン情報が正しいかをチェックする.
     *
     * @param email メールアドレス
     * @param password パスワード
     * @return ログイン成功の場合はUserオブジェクト,失敗の場合はnull
     */
    public User login(String email, String password){
        User user = userRepository.findByEmail(email);
        // 対象のユーザがいない場合はnullを返す
        if (user == null) {
            return null;
        }
        // パスワードが不一致だった場合はnullを返す
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        return user;
    }


}
