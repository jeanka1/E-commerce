package com.E_commerce.user.controller;

import com.E_commerce.product.model.Product;
import com.E_commerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String home(Model model){
        List<Product> product=productService.findAll();
        model.addAttribute("products",product);
        return "administrator/home";
    }
}
