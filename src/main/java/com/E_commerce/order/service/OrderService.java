package com.E_commerce.order.service;

import com.E_commerce.order.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();
    Order save (Order order);
}
