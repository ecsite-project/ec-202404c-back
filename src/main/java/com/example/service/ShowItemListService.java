package com.example.service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowItemListService {


    @Autowired
    private ItemRepository repository;

    public List<Item> getItemByType(String itemType) {
        return repository.findByType(itemType);
    }
}












