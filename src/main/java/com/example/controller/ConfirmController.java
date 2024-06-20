package com.example.controller;

import com.example.domain.Order;
import com.example.request.OrderIdRequest;
import com.example.service.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 注文確認画面を表示するコントローラー.
 *
 * @author io.yamanaka
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("")
public class ConfirmController {
    @Autowired
    private ConfirmService confirmService;

    /**
     * 注文確認画面を表示する.
     *
     * @param orderIdRequest 注文id
     * @return 注文
     */
    @PostMapping("/confirm")
    public ResponseEntity<Order> showConfirm(@RequestBody OrderIdRequest orderIdRequest){
        return new ResponseEntity<>(confirmService.findByOrderId(orderIdRequest.getId()), HttpStatus.CREATED);
    }
}
