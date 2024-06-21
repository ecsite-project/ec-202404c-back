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


    /**
     * orderIdから注文情報を取得する．
     *
     * @param orderId 注文id
     * @return 注文情報
     */
    public Order findById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    /**
     * userIdから注文情報を取得する．
     *
     * @param userId ユーザid
     * @return 注文情報
     */
    public Order findByUserId(Integer userId) {
        return orderRepository.findActiveOrderByUserId(userId);
    }

    /**
     * カートに商品を追加する.
     *
     * @param userId ユーザid
     * @param itemId 商品id
     * @param itemType 商品属性
     * @param quantity 数量
     * @param size 大きさ
     * @return 追加されたカート情報
     */
    public Order addItemToCart(Integer userId, Integer itemId, String itemType, Integer quantity,String size) {
        Order order = orderRepository.findActiveOrderByUserId(userId);
        System.out.println(order);
        if (order == null) {
            Integer orderId = orderRepository.createOrder(userId);
            orderRepository.addOrderItem(orderId, itemId, quantity, size);
            return orderRepository.findById(orderId);
        } else {
            orderRepository.addOrderItem(order.getId(), itemId, quantity, size);
            return orderRepository.findById(order.getId());
        }
    }

    /**
     * カートから指定した注文アイテムを削除し、アクティブな注文を返します。
     *
     * @param orderItemId 注文アイテムID
     * @param userId ユーザーID
     * @return アクティブな注文オブジェクト
     */
    public Order deleteOrderItem(Integer orderItemId, Integer userId) {
        // 指定したOrderItemを削除する
        orderRepository.deleteOrderItem(orderItemId);

        // ユーザーIDとステータスIDが1のアクティブな注文を取得する
        return orderRepository.findActiveOrderByUserId(userId);
    }
}
