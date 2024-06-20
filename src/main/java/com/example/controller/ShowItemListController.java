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
    public ResponseEntity<ItemTypeResponse> getItemDetails(@PathVariable String itemType, @RequestBody(required = false) String searchName) {
        // レスポンスオブジェクトの初期化
        ItemTypeResponse response = new ItemTypeResponse();
        // アイテムのリストを格納する変数
        List<Item> items;

        // itemTypeに基づいてアイテムを取得する
        switch (itemType.toLowerCase()) {
            case "top":
            case "bottom":
            case "set":
                if(searchName != null) {
                    // 検索文字列があれば曖昧検索
                    items = showItemListService.searchByNameContaining(itemType.toLowerCase(), searchName);
                }
                // 指定された種類（top, bottom, set）のアイテムを取得
                items = showItemListService.getItemByType(itemType.toLowerCase());
                break;
            default:
                // 不正なリクエストの場合、BAD_REQUEST（400エラー）を返す
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // レスポンスに取得したアイテムのリストを設定
        response.setItems(items);
        // 取得したアイテムのリストとHTTPステータスOK（200）を返す
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
