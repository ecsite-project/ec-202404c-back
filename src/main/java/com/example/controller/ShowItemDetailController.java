package com.example.controller;


import com.example.domain.Item;
import com.example.service.ShowItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品詳細一覧を表示するコントローラです.
 */
@RestController
@RequestMapping("/items")
public class ShowItemDetailController {

    @Autowired
    private ShowItemDetailService showItemDetailService;

    @GetMapping("/{type}")
    public Map<String,Object> showItemDetail(@PathVariable("type") String type){
        List<Object> typeItems = showItemDetailService.getTypeList(type);

        if(typeItems == null){
            //TODO null処理行う
            System.out.println("nullです");
        }
        Map<String, Object> itemsMap = new HashMap<>();
        itemsMap.put("items", typeItems.get(0));
        System.out.println("items : " + itemsMap);

        return itemsMap;
    }




}

