package com.example.controller;

import com.example.domain.LoginUser;
import com.example.request.FavoriteRequest;
import com.example.response.ItemTypeResponse;
import com.example.response.PreviewItemResponse;
import com.example.security.Authorize;
import com.example.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Favoriteコントローラークラス。
 * ユーザのお気に入り登録、解除、一覧取得を管理する。
 */
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    /**
     * お気に入りを追加する。
     *
     * @param request お気に入りリクエスト
     */
    @PostMapping("/add")
    public void addFavorite(@RequestBody FavoriteRequest request) {
        favoriteService.addFavorite(request.getUserId(), request.getItemId());
    }

    /**
     * お気に入りを解除する。
     *
     * @param request お気に入りリクエスト
     */
    @PostMapping("/delete")
    public void deleteFavorite(@RequestBody FavoriteRequest request) {
        favoriteService.deleteFavorite(request.getUserId(), request.getItemId());
    }

    /**
     * ログイン中のユーザのお気に入り商品一覧を取得する。
     *
     * @return お気に入り商品一覧
     */
    @Authorize
    @GetMapping("")
    public ItemTypeResponse getFavoritesByUserId(@AuthenticationPrincipal LoginUser loginUser) {
        return favoriteService.getFavoritesByUserId(loginUser.getUser().getId());
    }

    /**
     * ログイン中のユーザのお気に入り商品一覧を取得する。
     *
     * @return お気に入り商品一覧
     */
    @Authorize
    @GetMapping("/forpreview")
    public ResponseEntity<PreviewItemResponse> getPreviewItem(@AuthenticationPrincipal LoginUser loginUser) {
        PreviewItemResponse response = new PreviewItemResponse();
        response.setItems(favoriteService.getFavoritesByUserIdForPreview(loginUser.getUser().getId()));
        return ResponseEntity.ok(response);
    }
}
