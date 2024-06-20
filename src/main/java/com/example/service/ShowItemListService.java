package com.example.service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品一覧画面を操作するサービス.
 *
 * @author io.yamanaka
 *
 */
@Service
public class ShowItemListService {


    @Autowired
    private ItemRepository repository;

    /**
     * itemTypeに一致する商品を全件取得します.
     *
     * @param itemType 商品名
     * @return 全ての商品の情報一覧
     */
    public List<Item> getItemByType(String itemType) {
        return repository.findByType(itemType);
    }

    /**
     * 名前から商品を(曖昧)検索します.
     *
     * @param name 商品名
     * @return 検索された商品の情報一覧
     */
    public List<Item> searchByNameContaining(String name,String itemType) {
        return repository.searchByNameContaining(name, itemType);
    }

}












