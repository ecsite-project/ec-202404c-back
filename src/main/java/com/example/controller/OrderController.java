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
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@EnableAsync
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @Value("${credit-card-check-api}")
    private String creditCardCheckApi;

    @PostMapping("/card")
    public ResponseEntity<WebApiResponseObject> cardOrder(@RequestBody CreditCard creditCard, HttpServletResponse response) {
        CreditCard card = new CreditCard();
        BeanUtils.copyProperties(creditCard, card);
        System.out.println("card" + card);
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.postForObject(creditCardCheckApi, card, JsonNode.class);
        System.out.println(jsonNode);


        WebApiResponseObject responseObject = new WebApiResponseObject();
        String status = jsonNode.get("status").asText();
        String message = jsonNode.get("message").asText();
        String errorCode = jsonNode.get("error_code").asText();

        responseObject.setStatus(status);
        responseObject.setMessage(message);
        responseObject.setErrorCode(errorCode);

        if ("success".equals(status) && "E-00".equals(errorCode)) {
            return ResponseEntity.ok(responseObject);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
        }
    }

    @PostMapping("")
    public WebApiResponseObject order(@RequestBody OrderRequest orderRequest, HttpServletResponse response){
        Order order = new Order();
        Destination destination = new Destination();
        Address address = new Address();

        orderRequest.setPaymentMethodId(1);

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