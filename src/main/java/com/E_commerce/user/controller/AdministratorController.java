package com.E_commerce.user.controller;

import com.E_commerce.order.model.Order;
import com.E_commerce.order.service.OrderService;
import com.E_commerce.orderItems.service.OrderItemsService;
import com.E_commerce.product.model.Product;
import com.E_commerce.product.service.ProductService;
import com.E_commerce.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemsService orderItemsService;

    private final Logger log = LoggerFactory.getLogger(AdministratorController.class);

    @GetMapping("")
    public String home(Model model){
        List<Product> product=productService.findAll();
        model.addAttribute("products",product);
        return "administrator/home";
    }

    @GetMapping("/users")
    private String users(Model model){
        model.addAttribute("users",userService.findAll());

        return "administrator/usuarios";
    }

    @GetMapping("orders")
    public String orders(Model model){

        model.addAttribute("orders", orderService.findAll());

        return "administrator/orders";
    }
    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable Integer id){
        log.info("id de la orden: {}", id);

        Order order= orderService.findById(id).get();
        model.addAttribute("detail",order.getOrderItems());
        System.out.println(order.getOrderItems());
        return "administrator/detailsOrder";
    }
}
