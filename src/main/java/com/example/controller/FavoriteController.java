package com.example.controller;

import com.example.request.FavoriteRequest;
import com.example.response.ItemTypeResponse;
import com.example.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 指定したユーザIDのお気に入り商品一覧を取得する。
     *
     * @param userId ユーザID
     * @return お気に入り商品一覧
     */
    @GetMapping("/{userId}")
    public ItemTypeResponse getFavoritesByUserId(@PathVariable Integer userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }
}
