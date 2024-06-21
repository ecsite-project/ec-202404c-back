package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user){
        userRepository.insert(user);
    }

    /**
     * 入力されたメールアドレスがDBと重複していないかをチェックする.
     *
     * @param email 調べたいメールアドレス
     * @return 存在していればtrue,存在していなければfalse
     */
    public boolean checkExistEmail(String email) {
        if (userRepository.findByMailAddress(email) == null) {
            return false;
        }
        return true;
    }

}
