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
    public WebApiResponseObject order(@RequestBody OrderRequest orderRequest, HttpServletResponse response) {
        // OrderRequest から必要な情報を抽出して Order オブジェクトに設定
        Order order = new Order();
        Destination destination = new Destination();
        Address address = new Address();

        BeanUtils.copyProperties(orderRequest, order);
        BeanUtils.copyProperties(orderRequest, destination);
        BeanUtils.copyProperties(orderRequest, address);
        order.setId(orderRequest.getOrderId());

        // 注文処理を OrderService を使って行う
        orderService.order(order, destination, address);

        // メールの送信
        sendOrderConfirmationEmail(order, destination, address);

        // レスポンスの作成
        WebApiResponseObject responseObject = new WebApiResponseObject();
        responseObject.setStatus("success");
        responseObject.setMessage("Order placed successfully");
        responseObject.setErrorCode("E-00");

        return responseObject;
    }

    // 注文確認メールの送信処理
    private void sendOrderConfirmationEmail(Order order, Destination destination, Address address) {
        String subject = "注文完了メール";
        StringBuilder sb = new StringBuilder();

        // メール本文の組み立て
        sb.append(destination.getDestinationName()).append("様").append("\n\n");
        sb.append("ご注文ありがとうございます。\n");
        sb.append("ご注文内容は以下の通りです。\n\n");
        sb.append("===============================\n");
        sb.append("注文商品：\n");
        List<OrderItem> orderItemList = order.getItemList();
        for (OrderItem item : orderItemList) {
            sb.append("　　商品名：").append(item.getItem().getName()).append("\n");
            sb.append("　　サイズ：").append(item.getSize()).append("\n");
            sb.append("　　個数：").append(item.getQuantity()).append("点").append("\n");
            sb.append("　　1点当たりの価格：").append(item.getItem().getPrice()).append("円").append("\n");
        }
        sb.append("\n");
        sb.append("お届け先：").append(address.getPrefecture()).append(" ").append(address.getMunicipalities()).append(" ").append(address.getAddress()).append("\n");
        sb.append("お届け予定日時：").append(order.getDeliveryDate()).append("\n");
        sb.append("===============================\n\n");
        sb.append("またのご利用をお待ちしております。");

        String text = sb.toString();
        emailService.sendSimpleMessage(destination.getDestinationEmail(), subject, text);
    }
}
