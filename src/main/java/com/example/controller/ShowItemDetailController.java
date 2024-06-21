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
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class ShowItemDetailController {

    @Autowired
    private ShowItemDetailService showItemDetailService;

    /**
     * 商品詳細をidで検索して返す.setの場合，topとbottom に1以上整数が入る.
     *
     * @param ItemId 商品id
     * @return 商品詳細
     */
    @GetMapping("/{ItemId}")
    public ResponseEntity<ItemDetailResponse> showItemDetail(@PathVariable("ItemId") Integer ItemId) {
        return new ResponseEntity<>(showItemDetailService.getItemDetail(ItemId), HttpStatus.OK);
    }
}

