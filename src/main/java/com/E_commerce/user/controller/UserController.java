package com.E_commerce.user.controller;

import com.E_commerce.product.model.Product;
import com.E_commerce.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private final Logger log= LoggerFactory.getLogger(UserController.class);
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
}
