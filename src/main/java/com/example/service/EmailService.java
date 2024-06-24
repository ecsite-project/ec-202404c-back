package com.example.service;

import com.example.domain.Address;
import com.example.domain.Destination;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private OrderRepository orderRepository;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        // メールの送信先(お客様)を指定
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public Order getOrderInfo(Integer orderId){
        // メールに記載するため、注文情報を取得
        Order order = orderRepository.findById(orderId);
        return order;
    }

    @Async("taskExecutor")
    public void sendOrderConfirmationEmail(Destination destination, Address address, Order order) {
        String subject = "注文完了メール";
        StringBuilder sb = new StringBuilder();

        // メールに記載するため、注文情報を取得
        Order orderInfo = this.getOrderInfo(order.getId());

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
        this.sendSimpleMessage(destination.getDestinationEmail(), subject, text);
    }
}

