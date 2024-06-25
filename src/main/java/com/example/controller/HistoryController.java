package com.example.controller;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.request.OrderIdRequest;
import com.example.request.UserIdRequest;
import com.example.service.ConfirmService;
import com.example.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注文履歴画面を表示するコントローラー.
 *
 * @author io.yamanaka
 */
@RestController
@RequestMapping("")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * 注文履歴画面を表示する.
     *
     * @param userIdRequest ユーザid
     * @return ユーザIDに合致した注文のリスト
     */
    @PostMapping("/history")
    public ResponseEntity<List<Order>> showHistory(@RequestBody UserIdRequest userIdRequest){
        return new ResponseEntity<>(historyService.findByUserId(userIdRequest.getUserId()), HttpStatus.CREATED);
    }
}
