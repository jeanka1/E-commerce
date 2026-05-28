package com.E_commerce.user.controller;

import com.E_commerce.user.model.User;
import com.E_commerce.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clien")
public class ClienController {

    private final Logger log = LoggerFactory.getLogger(ClienController.class);

    @Autowired
    private UserService userService;

    //clien/registro
    @GetMapping("/register")
    public String create(){
        return "/user/registroUser";
    }

    @PostMapping("/save")
    public String save(User user){
        log.info("usuario regitro {}", user);
        user.setType("USER");
        userService.save(user);
        return "redirect:/";
    }
}
