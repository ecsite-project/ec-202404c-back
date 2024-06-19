package com.example.controller;

import com.example.domain.Order;
import com.example.service.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 注文確認画面を表示するコントローラです.
 *
 * @author io.yamanaka
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/confirm")
public class ConfirmController {
    @Autowired
    private ConfirmService confirmService;

//    @GetMapping
//    public ResponseEntity<User> showUser(Integer orderId){
//        user.setName("John Doe");
//        user.setEmail("john.doe@example.com");
//        user.setPassword("password123");
//        user.setZipcode("12345");
//        user.setAddress("123 Main St");
//        user.setTelephone("123-456-7890");
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
//TODO curlコマンドで実行確認
    @PostMapping("")
    public ResponseEntity<Order> showConfirm(@RequestBody Integer orderId){
        Order order  = confirmService.showConfirm(orderId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
