package com.example.controller;

import com.example.domain.*;
import com.example.response.ItemTypeResponse;
import com.example.security.NonAuthorize;
import com.example.service.ShowItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    
    /**
     * 商品タイプに該当する商品を取得する.
     *
     * @param itemType 商品タイプ．top, set, bottom が有効
     * @return 商品一覧
     */
    @NonAuthorize
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
