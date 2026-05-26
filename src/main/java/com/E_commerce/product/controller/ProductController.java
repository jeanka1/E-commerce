package com.E_commerce.product.controller;


import com.E_commerce.product.model.Product;
import com.E_commerce.product.service.ProductService;
import com.E_commerce.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("products",productService.findAll());
        return "/products/show";
    }

    @GetMapping("/create")
    public String createProduct(){
        return "/products/createProduct";
    }

    @PostMapping("/save")
    public String save(Product product){
        LOGGER.info("este es el objeto product {}",product);
        User u= new User(1,"","","","","","","");
        product.setUser(u);
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
       Product product= new Product();
        Optional<Product> optionalProduct=productService.get(id);
        product=optionalProduct.get();
        LOGGER.info("producto buscado: {}",product);
        model.addAttribute("product", product);

        return "products/editProduct";
    }
    @PostMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/products";
    }
}
