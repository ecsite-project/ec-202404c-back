package com.example.service;

import com.example.domain.Order;
import com.example.repository.AddressRepository;
import com.example.repository.DestinationRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 注文履歴画面を扱うサービス.
 *
 * @author io.yamanaka
 */
@Service
@Transactional
public class HistoryService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Order> findByUserId(Integer userId){
        return orderRepository.findByUserId(userId);
    }
}
