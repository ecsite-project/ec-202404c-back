package com.example.controller;

import com.example.domain.User;
import com.example.request.RegisterUserRequest;
import com.example.security.NonAuthorize;
import com.example.service.RegisterUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // ユーザ登録のエンドポイント
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("リクエストボディが不正です。");
        }

        // メールアドレスの重複チェック
        if (registerUserService.checkExistEmail(registerUserRequest.getEmail())) {
            return new ResponseEntity<>("そのメールアドレスはすでに使われています。", HttpStatus.CONFLICT);
        }

        // ユーザ登録処理
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest, user);
        registerUserService.registerUser(user);

        return new ResponseEntity<>("ユーザ登録が完了しました。", HttpStatus.CREATED);
    }


    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        if (registerUserService.checkExistEmail(email)) {
            return new ResponseEntity<>("そのメールアドレスはすでに使われています。", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok().build();
    }
}







