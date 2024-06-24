package com.example.controller;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.request.AddItemToCartRequest;
import com.example.request.DeleteItemFromCartRequest;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * カートを管理するコントローラー.
 *
 * @author reon.hatsuda
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 該当する注文情報を表示する.
     *
     * @param orderId 注文id
     * @return 注文
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> showCartByOrder(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(cartService.findById(orderId), HttpStatus.OK);
    }

    /**
     * 該当する注文情報を表示する.
     *
     * @param userId ユーザid
     * @return 注文
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Order> showCarByUser(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(cartService.findByUserId(userId), HttpStatus.OK);
    }

    /**
     *
     * リクエストに応じてカートに商品を追加する.
     *
     * @param request リクエスト
     * @return 追加したカート情報
     */
    @PostMapping("/add")
    public ResponseEntity<Order> addItemToCart(@RequestBody AddItemToCartRequest request) {
        Order cart = cartService.addItemToCart(request.getUserId(), request.getItemId(), request.getItemType(), request.getQuantity(), request.getSize());;
        return ResponseEntity.ok(cart);
    }

    /**
     * カートからorderItemを消去してカート情報を返す.
     *
     * @param request 削除したいorderItemのリクエスト
     * @return 削除後のカート情報
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Order> deleteItemFromCart(@RequestBody DeleteItemFromCartRequest request) {
        // サービスを利用して注文アイテムを削除し、アクティブな注文を取得する
        Order cart = cartService.deleteOrderItem(request.getOrderItemId(), request.getUserId());

        return ResponseEntity.ok(cart);
    }
}
