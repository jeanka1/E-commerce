package com.E_commerce.order.service;

import com.E_commerce.order.model.Order;
import com.E_commerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl  implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public String generarNumberOrder(){

        int number=0;
        String numberConcatenado="";

        List<Order> orders= findAll();
        List<Integer> numbers= new ArrayList<Integer>();

        orders.stream().forEach(o -> numbers.add( Integer.parseInt( o.getNumber()) ));

        if(orders.isEmpty()){
            number=1;
        }else{
            number=numbers.stream().max(Integer::compare).get();
            number++;
        }

        if(number<10){
            numberConcatenado="000000000"+String.valueOf(number);

        }else if(number<100){
            numberConcatenado="00000000"+String.valueOf(number);
        }else if(number<1000){
            numberConcatenado="0000000"+String.valueOf(number);
        }else if(number<10000){
            numberConcatenado="000000"+String.valueOf(number);
        }

        return numberConcatenado;
    }

}
