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

    public Order addItemToCart(Integer userId, Integer itemId, String itemType, Integer quantity,String size) {
        Order order = orderRepository.findActiveOrderByUserId(userId);
        System.out.println(order);
        if (order == null) {
            System.out.println("insert order");
            Integer orderId = orderRepository.createOrder(userId);
            System.out.println("insert orderItem");
            orderRepository.addOrderItem(orderId, itemId, quantity, size);
            return orderRepository.findById(orderId);
        } else {
            System.out.println("found order");
            System.out.println("insert orderItem");
            orderRepository.addOrderItem(order.getId(), itemId, quantity, size);
            return orderRepository.findById(order.getId());
        }
    }
}
