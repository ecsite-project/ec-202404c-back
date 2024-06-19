package com.example.service;


import com.example.domain.Bottom;
import com.example.domain.Item;
import com.example.repository.BottomRepository;
import com.example.repository.ItemRepository;
import com.example.repository.TopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ShowItemDetailService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TopRepository topRepository;

    @Autowired
    private BottomRepository bottomRepository;


    /**
     * タイプごとの商品情報一覧を取得する.
     *
     * @param type 商品タイプ
     * @return List<Item> 商品一覧
     */
    public List<Object> getTypeList(String type) {
        if (type.equals("Top")) {
            return topRepository.findAll();
        } else if (type.equals("Bottom")) {
            return bottomRepository.findAll();
        }
        return null;
    }


}
