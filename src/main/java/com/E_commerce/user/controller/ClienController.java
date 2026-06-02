package com.E_commerce.user.controller;

import com.E_commerce.order.model.Order;
import com.E_commerce.order.service.OrderService;
import com.E_commerce.user.model.User;
import com.E_commerce.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clien")
public class ClienController {

    private final Logger log = LoggerFactory.getLogger(ClienController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    //clien/registro
    @GetMapping("/register")
    public String create(){
        return "/user/registroUser";
    }

    @PostMapping("/save")
    public String save(User user){
        log.info("usuario regitro {}", user);
        user.setType("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/acceder")
    public String acceder(User user, HttpSession session){
        log.info("accesos : {}", user);

        Optional<User> user1=userService.findById(Integer.parseInt(session.getAttribute("iduser").toString()));
        //log.info("usuario de db:{}", user1.get());

        if(user1.isPresent()){
            session.setAttribute("iduser",user1.get().getId());
            if(user1.get().getType().equals("ADMIN")){
                return "redirect:/administrator";
            }else{
                return "redirect:/";
            }
        }else {
            log.info("usuario no exite");
        }

        return "redirect:/";
    }
    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session){
        model.addAttribute("session",session.getAttribute("iduser"));

        User user=userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
        List<Order> order=orderService.findByUser(user);

        model.addAttribute("order", order);

        return "user/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id,HttpSession session, Model model){
        log.info("id de la orden: {}", id);
        //session
        Optional<Order> order=orderService.findById(id);
        model.addAttribute("detalles",order.get().getOrderItems());
        model.addAttribute("session",session.getAttribute("iduser"));

        return "user/detalleCompra";
    }

    @GetMapping("/close")
    public String closeSesion(HttpSession session){
        session.removeAttribute("iduser");
        return "redirect:/";
    }
}
