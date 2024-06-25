package com.example.controller;

import com.example.domain.LoginUser;
import com.example.response.ItemDetailResponse;
import com.example.security.JsonWebTokenUtil;
import com.example.security.SecurityConstants;
import com.example.service.ShowItemDetailService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private JsonWebTokenUtil jsonWebTokenUtil;

    /**
     * 商品詳細をidで検索して返す.setの場合，topとbottom に1以上整数が入る.
     *
     * @param ItemId 商品id
     * @return 商品詳細
     */
    @GetMapping("/{ItemId}")
    public ResponseEntity<ItemDetailResponse> showItemDetail(@PathVariable("ItemId") Integer ItemId, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty()) {
            return new ResponseEntity<>(showItemDetailService.getItemDetail(ItemId, null), HttpStatus.OK);
        }
        // Bearer tokenの形式であることをチェック
        if (authorization.indexOf("Bearer ") != 0) {
            return new ResponseEntity<>(showItemDetailService.getItemDetail(ItemId, null), HttpStatus.OK);
        }

        // ログインしているはず
        // Authorizationの最初に付加されている「Bearer 」を除去し、アクセストークンのみ取り出し
        String accessToken = authorization.replace("Bearer ", "");
//		String accessToken = authorization.substring(7);
        System.out.println("accessToken : " + accessToken);
        String userId = jsonWebTokenUtil.getIdFromToken(accessToken, SecurityConstants.JWT_KEY);
        System.out.println("userId : " + userId);
        return new ResponseEntity<>(showItemDetailService.getItemDetail(ItemId, Integer.parseInt(userId)), HttpStatus.OK);
    }
}

