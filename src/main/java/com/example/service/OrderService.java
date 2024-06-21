package com.example.service;

import com.example.domain.Address;
import com.example.domain.Destination;
import com.example.domain.Order;
import com.example.repository.AddressRepository;
import com.example.repository.DestinationRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注文をするためのサービスクラスです.
 *
 * @author io.yamanaka
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private AddressRepository addressRepository;

    public void order(Order order, Destination destination, Address address){
        if (order.getPaymentMethodId() == 1){
            order.setStatusId(1);
        }
        if (order.getPaymentMethodId() == 2){
            order.setStatusId(2);
        }
        orderRepository.update(order);
        Integer addressId =  addressRepository.insert(address);
        destination.setAddressId(addressId);
        destinationRepository.insert(destination);
    }
}