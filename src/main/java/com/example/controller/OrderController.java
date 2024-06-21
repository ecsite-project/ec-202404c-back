package com.example.controller;

import com.example.WebApiResponseObject;
import com.example.domain.Address;
import com.example.domain.Destination;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.request.OrderRequest;
import com.example.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 注文をするコントローラです.
 *
 * @author io.yamanaka
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public WebApiResponseObject order(@RequestBody OrderRequest orderRequest, HttpServletResponse response, @RequestParam String email){
        Order order = new Order();
        Destination destination = new Destination();
        Address address = new Address();
        BeanUtils.copyProperties(orderRequest,order);
        BeanUtils.copyProperties(orderRequest,destination);
        BeanUtils.copyProperties(orderRequest, address);
        orderService.order(order, destination, address);

        // 成功情報をレスポンス
        WebApiResponseObject webApiResponseObject = new WebApiResponseObject();
        webApiResponseObject.setStatus("success");
        webApiResponseObject.setMessage("OK.");
        webApiResponseObject.setErrorCode("E-00");


        // 以下にメールの主題、内容を入力
        // textに注文者名や注文商品、サイズを入れたいため、@RequestParamで全て受け取る
        String subject = "注文完了メール";
        StringBuilder sb = new StringBuilder();

        sb.append("").append("様").append("\n\n");
        sb.append("ご注文ありがとうございます。\n");
        sb.append("ご注文内容は以下の通りです。\n");
        sb.append("注文商品：").append("").append("\n");
        sb.append("お届け先：").append("").append("\n");
        sb.append("お届け予定日時：").append("").append("\n\n");
        sb.append("またのご利用をお待ちしております。");

        String text = sb.toString();
        emailService.sendSimpleMessage(email, subject, text);

        return webApiResponseObject;
    }
}