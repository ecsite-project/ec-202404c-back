package com.example.controller;

import com.example.WebApiResponseObject;
import com.example.domain.*;
import com.example.request.OrderRequest;
import com.example.service.EmailService;
import com.example.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注文をするコントローラです.
 *
 * @author io.yamanaka
 */
@EnableAsync
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

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
        System.out.println("★終わったよ");
        // メール送信を非同期で行う
        sendOrderConfirmationEmail(destination, address, order);
        System.out.println("★メール終わったよ");
        return webApiResponseObject;
    }

    @Async
    public void sendOrderConfirmationEmail(Destination destination, Address address, Order order) {
        String subject = "注文完了メール";
        StringBuilder sb = new StringBuilder();

        // メールに記載するため、注文情報を取得
        Order orderInfo = emailService.getOrderInfo(order.getId());

        sb.append(destination.getDestinationName()).append("様").append("\n\n");
        sb.append("ご注文ありがとうございます。\n");
        sb.append("ご注文内容は以下の通りです。\n\n");
        sb.append("===============================\n");
        sb.append("注文商品：\n");
        List<OrderItem> orderItemList = orderInfo.getItemList();
        for (OrderItem item : orderItemList){
            sb.append("　　商品名：").append(item.getItem().getName()).append("\n");
            sb.append("　　サイズ：").append(item.getSize()).append("\n");
            sb.append("　　個数：").append(item.getQuantity()).append("点").append("\n");
            sb.append("　　1点当たりの価格：").append(item.getItem().getPrice()).append("円").append("\n");
        }
        sb.append("\n");
        sb.append("お届け先：").append(address.getPrefecture() + " "+ address.getMunicipalities() + " "+ address.getAddress()).append("\n");
        sb.append("お届け予定日時：").append(order.getDeliveryDate()).append("\n");
        sb.append("===============================\n\n");
        sb.append("またのご利用をお待ちしております。");

        String text = sb.toString();
        emailService.sendSimpleMessage(destination.getDestinationEmail(), subject, text);
    }
}