package com.E_commerce.user.controller;

import com.E_commerce.order.model.Order;
import com.E_commerce.order.service.OrderService;
import com.E_commerce.orderItems.model.OrderItems;
import com.E_commerce.orderItems.service.OrderItemsService;
import com.E_commerce.product.model.Product;
import com.E_commerce.product.service.ProductService;
import com.E_commerce.user.model.User;
import com.E_commerce.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    // para almacenar los detalles dela orden
    List<OrderItems> items = new ArrayList<OrderItems>();

    // va almacenar los datos de la oreden
    Order order = new Order();

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "user/homeUser";
    }

    @GetMapping("productHome/{id}")
    public String productHome(@PathVariable Integer id, Model model) {
        log.info("id product enviado como parametro {}", id);
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();
        model.addAttribute("products", product);
        return "/user/productHome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, Integer quantity, Model model) {
        OrderItems orderItems = new OrderItems();
        Product product = new Product();
        double sumaTotal = 0;

        Optional<Product> optionalProduct = productService.get(id);
        log.info("producto añadido {}", optionalProduct.get());
        log.info("cantidad {}", quantity);
        product = optionalProduct.get();

        orderItems.setQuantity(quantity);
        orderItems.setPrice(product.getPrice());
        orderItems.setName(product.getName());
        orderItems.setTotal(product.getPrice() * quantity);
        orderItems.setProduct(product);

        // validar que el producto no se añada dos veces
        Integer idProduct = product.getId();
        boolean ingresado = items.stream().anyMatch(p -> p.getProduct().getId() == idProduct);

        if (!ingresado) {
            items.add(orderItems);
        }


        sumaTotal = items.stream().mapToDouble(dt -> dt.getTotal()).sum();

        order.setTotal(sumaTotal);

        model.addAttribute("cart", items);
        model.addAttribute("order", order);

        return "user/cart";
    }

    // quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteCart(@PathVariable Integer id, Model model) {

        //lista nueva de productos
        List<OrderItems> itemsNuevo = new ArrayList<OrderItems>();

        for (OrderItems orderItems : items) {

            if (orderItems.getProduct().getId() != id) {
                itemsNuevo.add(orderItems);

            }
        }
        // poner la nueva lista con los productos restantes
        items = itemsNuevo;

        double sumaTotal = 0;
        sumaTotal = items.stream().mapToDouble(dt -> dt.getTotal()).sum();

        order.setTotal(sumaTotal);

        model.addAttribute("cart", items);
        model.addAttribute("order", order);

        return "user/cart";
    }

    @GetMapping("getCart")
    public String getCart(Model model) {
        model.addAttribute("cart", items);
        model.addAttribute("order", order);
        return "/user/cart";
    }

    @GetMapping("/verOrder")
    public String verOrder(Model model){

        User user= userService.findAllId(1).get();

        model.addAttribute("cart", items);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        return "/user/resumenorder";
    }

    //guardar la orden
    @GetMapping("saveOrder")
    public String saveOrder(){
        Date crationDate= new Date();
        order.setCreationDate(crationDate);
        order.setNumber(orderService.generarNumberOrder());

        //usuario
        User user= userService.findAllId(1).get();
        order.setUse(user);
        orderService.save(order);

        //guardar detalles
        for (OrderItems dt:items){
            dt.setOrder(order);
            orderItemsService.save(dt);
        }

        // limppiar
         order = new Order();
        items.clear();
        return "redirect:/";
    }
}
