package com.example.controller;

import com.example.domain.*;
import com.example.request.RegisterUserRequest;
import com.example.response.ItemTypeResponse;
import com.example.service.ShowItemListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品一覧表示を行うコントローラです.
 *
 * @author haruka.yamaneki
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/items")
public class ShowItemListController {
    @Autowired
    private ShowItemListService showItemListService;


    @GetMapping("/{itemType}")
    public ResponseEntity<ItemTypeResponse> getItemDetails(@PathVariable String itemType) {
        ItemTypeResponse response = new ItemTypeResponse();
        List<Item> items;

        switch (itemType.toLowerCase()) {
            case "top":
            case "bottom":
            case "set":
                items = showItemListService.getItemByType(itemType.toLowerCase());
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response.setItems(items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
