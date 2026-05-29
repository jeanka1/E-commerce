package com.E_commerce.order.service;

import com.E_commerce.order.model.Order;
import com.E_commerce.user.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Optional<Order> findById(Integer id);
    Order save (Order order);
    String generarNumberOrder();
    List<Order> findByUser(User user);
}
