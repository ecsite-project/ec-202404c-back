package com.example.controller;

import com.example.domain.User;
import com.example.request.RegisterUserRequest;
import com.example.service.RegisterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 新規にユーザ情報を登録するコントローラです.
 *
 * @author haruka.yamaneki
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/users")
public class ResisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

    @GetMapping
    public ResponseEntity<User> showUser(User user){
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setZipcode("12345");
        user.setAddress("123 Main St");
        user.setTelephone("123-456-7890");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public void registerUser(RegisterUserRequest registerUserRequest){
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest,user);
        registerUserService.registerUser(user);
    }

}





