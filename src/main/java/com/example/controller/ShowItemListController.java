package com.example.controller;

import com.example.domain.Item;
import com.example.domain.User;
import com.example.request.RegisterUserRequest;
import com.example.service.ShowItemListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品一覧表示を行うコントローラです.
 *
 * @author haruka.yamaneki
 */
@RestController
@RequestMapping("/items")
public class ShowItemListController {
    @Autowired
    private ShowItemListService showItemListService;

    @GetMapping
    public ResponseEntity<List<Item>> showItem(Item item){
        List<Item> itemList = showItemListService.showItem();
        return new ResponseEntity<>(itemList, HttpStatus.CREATED);
    }

}
