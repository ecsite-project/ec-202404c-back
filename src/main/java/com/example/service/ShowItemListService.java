package com.example.service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品一覧を扱うサービス.
 *
 * @author reon.hatsuda
 */
@Service
public class ShowItemListService {


    @Autowired
    private ItemRepository repository;

    /**
     * 商品の種類に応じて情報を取得する.
     *
     * @param itemType　商品タイプ
     * @return 該当の商品タイプの全ての商品
     */
    public List<Item> getItemByType(String itemType) {
        return repository.findByType(itemType);
    }

    public List<Item> searchItemByTypeAndName(String itemType, String query){
        System.out.println(repository.searchByNameAndTypeContaining(itemType, query));
        return repository.searchByNameAndTypeContaining(itemType, query);
    }
}












