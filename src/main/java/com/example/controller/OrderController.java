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
    public WebApiResponseObject order(@RequestBody OrderRequest orderRequest, HttpServletResponse response){
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

        return webApiResponseObject;
    }
}