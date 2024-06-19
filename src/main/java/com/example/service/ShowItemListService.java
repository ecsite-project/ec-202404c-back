package com.example.service;

import com.example.domain.Top;
import com.example.domain.Bottom;
import com.example.domain.Set;
import com.example.repository.TopRepository;
import com.example.repository.BottomRepository;
import com.example.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ShowItemListService {
    @Autowired
    private TopRepository topRepository;

    @Autowired
    private BottomRepository bottomRepository;

    @Autowired
    private SetRepository setRepository;

    public List<Object> getAllTops() {
        return Collections.singletonList(topRepository.findAll());
    }

    public List<Object> getAllBottoms() {
        return Collections.singletonList(bottomRepository.findAll());
    }

    public List<Object> getAllSets() {
        return Collections.singletonList(setRepository.findAll());
    }
}












