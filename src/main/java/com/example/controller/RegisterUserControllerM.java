package com.example.controller;

import com.example.domain.RegisterUserRequest;
import com.example.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/users")
public class RegisterUserControllerM {

    @GetMapping
    public ResponseEntity<User> showUser(){
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setZipcode("12345");
        user.setAddress("123 Main St");
        user.setTelephone("123-456-7890");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserRequest request){
        System.out.println(request);
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
