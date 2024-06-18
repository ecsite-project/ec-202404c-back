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
import org.springframework.web.bind.annotation.*;

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
    // TODO searchNameという変数をReact側でも合わせる
    public ResponseEntity<List<Item>> showItem(Item item, @RequestBody String searchName){
        List<Item> itemList = null;
        if(searchName == null) {
            // 検索文字列が空なら全件検索
            itemList = showItemListService.showItem();
        } else {
            // 検索文字列があれば曖昧検索
            itemList = showItemListService.searchByNameContaining(searchName);
        }
        return new ResponseEntity<>(itemList, HttpStatus.CREATED);
    }
}
