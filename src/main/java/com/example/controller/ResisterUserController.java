package com.example.controller;

import com.example.domain.User;
import com.example.request.RegisterUserRequest;
import com.example.security.NonAuthorize;
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

    /**
     * 新規ユーザを登録します。
     *
     * @param registerUserRequest 登録するユーザのリクエストオブジェクト
     * @return 登録結果を示すレスポンスエンティティ
     */
    @NonAuthorize
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest,user);

        // メールアドレス重複チェック
        if (registerUserService.checkExistEmail(user.getEmail())) {
            System.out.println("そのメールアドレスはすでに使われています。");
            return new ResponseEntity<>("そのメールアドレスはすでに使われています。", HttpStatus.CONFLICT);
        }

        registerUserService.registerUser(user);
        return new ResponseEntity<>("ユーザ登録がした。", HttpStatus.CREATED);
    }
}