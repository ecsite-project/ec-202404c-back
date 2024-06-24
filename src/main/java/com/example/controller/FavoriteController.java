package com.example.controller;

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
     * @param userId ユーザID
     * @param itemId 商品ID
     */
    @PostMapping("/add")
    public void addFavorite(@RequestParam Integer userId, @RequestParam Integer itemId) {
        favoriteService.addFavorite(userId, itemId);
    }

    /**
     * お気に入りを解除する。
     *
     * @param userId ユーザID
     * @param itemId 商品ID
     */
    @PostMapping("/delete")
    public void deleteFavorite(@RequestParam Integer userId, @RequestParam Integer itemId) {
        favoriteService.deleteFavorite(userId, itemId);
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
