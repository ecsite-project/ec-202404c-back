package com.example.service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShowItemListService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> showItem(){
        return itemRepository.findAll();
    }

}
