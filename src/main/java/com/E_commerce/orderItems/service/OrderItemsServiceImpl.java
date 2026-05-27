package com.E_commerce.orderItems.service;

import com.E_commerce.orderItems.model.OrderItems;
import com.E_commerce.orderItems.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    private OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItems save(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }
}
