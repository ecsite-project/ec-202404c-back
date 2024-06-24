package com.example.controller;

import com.example.domain.LoginUser;
import com.example.response.ItemDetailResponse;
import com.example.service.ShowItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


/**
 * 商品詳細一覧を表示するコントローラです.
 *
 * @author reon.hatsuda
 */
@RestController
@RequestMapping("/item")
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
    public ResponseEntity<ItemDetailResponse> showItemDetail(@PathVariable("ItemId") Integer ItemId, @AuthenticationPrincipal LoginUser loginUser) {
        System.out.println("" + loginUser.getUser());
        return new ResponseEntity<>(showItemDetailService.getItemDetail(ItemId, null), HttpStatus.OK);
    }
}

