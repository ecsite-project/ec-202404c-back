package com.example.service;

import com.example.repository.FavoriteRepository;
import com.example.response.ItemTypeResponse;
import com.example.response.PreviewItem;
import com.example.response.PreviewItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Favoriteサービスクラス。
 * ユーザのお気に入り登録、解除、一覧取得を管理する。
 */
@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    /**
     * お気に入りを追加する。
     *
     * @param userId ユーザID
     * @param itemId 商品ID
     */
    @Transactional
    public void addFavorite(Integer userId, Integer itemId) {
        favoriteRepository.addFavorite(userId, itemId);
    }

    /**
     * お気に入りを解除する。
     *
     * @param userId ユーザID
     * @param itemId 商品ID
     */
    @Transactional
    public void deleteFavorite(Integer userId, Integer itemId) {
        favoriteRepository.removeFavorite(userId, itemId);
    }

    /**
     * 指定したユーザIDのお気に入り商品一覧を取得する。
     *
     * @param userId ユーザID
     * @return お気に入り商品一覧
     */
    public ItemTypeResponse getFavoritesByUserId(Integer userId) {
        return favoriteRepository.getFavoritesByUserId(userId);
    }

    /**
     * 指定したユーザIDのお気に入り商品一覧を取得する。
     *
     * @param userId ユーザID
     * @return お気に入り商品一覧
     */
    public List<PreviewItem> getFavoritesByUserIdForPreview(Integer userId) {
        return favoriteRepository.getFavoritesByUserIdForPreview(userId);
    }
}
