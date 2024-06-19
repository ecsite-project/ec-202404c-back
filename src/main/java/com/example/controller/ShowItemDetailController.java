package com.example.controller;


import com.example.response.ItemDetailResponse;
import com.example.service.ShowItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 商品詳細一覧を表示するコントローラです.
 */
@RestController
@RequestMapping("/item")
public class ShowItemDetailController {

    @Autowired
    private ShowItemDetailService showItemDetailService;

//    @GetMapping("/{type}")
//    public Map<String,Object> showItemDetail(@PathVariable("type") String type){
//        List<Object> typeItems = showItemDetailService.getTypeList(type);
//
//        if(typeItems == null){
//            //TODO null処理行う
//            System.out.println("nullです");
//        }
//        Map<String, Object> itemsMap = new HashMap<>();
//        itemsMap.put("items", typeItems.get(0));
//        System.out.println("items : " + itemsMap);
//
//        return itemsMap;
//    }

    @GetMapping("/{ItemId}")
    public ResponseEntity<ItemDetailResponse> showItemDetail(@PathVariable("ItemId") Integer ItemId) {
        return new ResponseEntity<>(showItemDetailService.getItemDetail(ItemId), HttpStatus.OK);
    }
}

