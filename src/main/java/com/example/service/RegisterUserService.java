package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ登録に関するサービス.
 *
 * @author io.yamanaka
 */
@Service
@Transactional
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user){
        // パスワードをハッシュ化
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.insert(user);
    }
}
