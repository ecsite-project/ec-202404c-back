package com.example.controller;

import com.example.domain.Address;
import com.example.domain.Destination;
import com.example.domain.Order;
import com.example.request.OrderRequest;
import com.example.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void order(@RequestBody OrderRequest orderRequest){
        Order order = new Order();
        Destination destination = new Destination();
        Address address = new Address();
        BeanUtils.copyProperties(orderRequest,order);
        BeanUtils.copyProperties(orderRequest,destination);
        BeanUtils.copyProperties(orderRequest, address);
        orderService.order(order, destination, address);
    }
}