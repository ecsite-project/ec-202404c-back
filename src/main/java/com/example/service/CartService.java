package com.example.service;


import com.example.domain.Order;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * カートに関するサービス.
 *
 * @author reon.hatsuda
 */
@Service
@Transactional
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findById(Integer orderId) {
        return orderRepository.findById(orderId);
    }
}
