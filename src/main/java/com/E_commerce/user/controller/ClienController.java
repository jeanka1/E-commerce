package com.E_commerce.user.controller;

import com.E_commerce.user.model.User;
import com.E_commerce.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/acceder")
    public String acceder(User user, HttpSession session){
        log.info("accesos : {}", user);

        Optional<User> user1=userService.findByEmail(user.getEmail());
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
}
