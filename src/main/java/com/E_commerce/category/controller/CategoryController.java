package com.E_commerce.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("")
    public String showC(){
        return "/products/showC";
    }

    @GetMapping("/createCategories")
    public String createCategory(){
        return "/products/createCategory";
    }

}
