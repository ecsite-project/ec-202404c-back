package com.example.service;


import com.example.repository.ItemRepository;
import com.example.response.ItemDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品詳細を行うサービス.
 *
 * @author reon.hatsuda
 */
@Service
@Transactional
public class ShowItemDetailService {
    @Autowired
    private ItemRepository itemRepository;

    /**
     * idによって情報を取得する.
     *
     * @param itemId　商品id
     * @return　商品詳細
     */
    public ItemDetailResponse getItemDetail(Integer itemId){
        return itemRepository.findById(itemId);
    }
}
