package com.example.controller;

import com.example.WebApiResponseObject;
import com.example.domain.*;
import com.example.request.OrderRequest;
import com.example.service.EmailService;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;



@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @Value("${credit-card-check-api}")
    private String creditCardCheckApi;

    @PostMapping("/card")
    public ResponseEntity<WebApiResponseObject> cardOrder(@RequestBody OrderRequest orderRequest, HttpServletResponse response) {
        CreditCard card = new CreditCard();
        BeanUtils.copyProperties(orderRequest, card);

        // ここで必要に応じてクレジットカードのバリデーションや他の処理を行う
        // 外部のクレジットカードチェックAPIを呼び出す
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.postForObject(creditCardCheckApi, card, JsonNode.class);

        // レスポンスの作成
        WebApiResponseObject responseObject = new WebApiResponseObject();
        responseObject.setStatus("success");
        responseObject.setMessage("Card validation successful");
        responseObject.setErrorCode("E-00");

        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("")
    public WebApiResponseObject order(@RequestBody OrderRequest orderRequest, HttpServletResponse response){
        Order order = new Order();
        Destination destination = new Destination();
        Address address = new Address();

        BeanUtils.copyProperties(orderRequest,order);
        BeanUtils.copyProperties(orderRequest,destination);
        BeanUtils.copyProperties(orderRequest, address);
        order.setId(orderRequest.getOrderId());
        orderService.order(order, destination, address);

        // 成功情報をレスポンス
        WebApiResponseObject webApiResponseObject = new WebApiResponseObject();
        webApiResponseObject.setStatus("success");
        webApiResponseObject.setMessage("OK.");
        webApiResponseObject.setErrorCode("E-00");

        // メール送信を非同期で行う
        emailService.sendOrderConfirmationEmail(destination, address, order);


        return webApiResponseObject;
    }
}