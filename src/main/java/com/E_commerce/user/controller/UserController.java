package com.E_commerce.user.controller;

import com.E_commerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("products",productService.findAll());
        return "user/homeUser";
    }
}
