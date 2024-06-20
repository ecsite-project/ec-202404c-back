package com.example.service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注文確認画面を表示するためのサービスクラスです.
 *
 * @author io.yamanaka
 */
@Service
@Transactional
public class ConfirmService {
    @Autowired
    private OrderRepository orderRepository;

    public Order findByOrderId(Integer orderId){
        return orderRepository.findById(orderId);
    }
}
