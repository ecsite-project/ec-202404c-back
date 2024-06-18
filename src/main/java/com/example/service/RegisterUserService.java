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
}
