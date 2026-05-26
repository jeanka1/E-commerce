package com.E_commerce.user.controller;

import com.E_commerce.order.model.Order;
import com.E_commerce.orderItems.model.OrderItems;
import com.E_commerce.product.model.Product;
import com.E_commerce.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private final Logger log= LoggerFactory.getLogger(UserController.class);

    // para almacenar los detalles dela orden
    List<OrderItems> items=new ArrayList<OrderItems>();

    // va almacenar los datos de la oreden
    Order order=new Order();

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("products",productService.findAll());
        return "user/homeUser";
    }

    @GetMapping("productHome/{id}")
    public String productHome(@PathVariable Integer id, Model model){
        log.info("id product enviado como parametro {}",id);
        Product product=new Product();
        Optional<Product> optionalProduct= productService.get(id);
        product=optionalProduct.get();
        model.addAttribute("products",product);
        return "/user/productHome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, Integer quantity, Model model){
        OrderItems orderItems= new OrderItems();
        Product product= new Product();
        double sumaTotal=0;

        Optional<Product> optionalProduct=productService.get(id);
        log.info("producto añadido {}", optionalProduct.get());
        log.info("cantidad {}", quantity);
        product=optionalProduct.get();

        orderItems.setQuantity(quantity);
        orderItems.setPrice(product.getPrice());
        orderItems.setName(product.getName());
        orderItems.setTotal(product.getPrice()*quantity);
        orderItems.setProduct(product);

        items.add(orderItems);

        sumaTotal=items.stream().mapToDouble(dt->dt.getTotal()).sum();

        order.setTotal(sumaTotal);

        model.addAttribute("cart",items);
        model.addAttribute("order",order);

        return "user/cart";
    }
}
