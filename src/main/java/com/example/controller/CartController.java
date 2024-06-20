package com.example.controller;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> showCart(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(cartService.findById(orderId), HttpStatus.CREATED);
    }

}
