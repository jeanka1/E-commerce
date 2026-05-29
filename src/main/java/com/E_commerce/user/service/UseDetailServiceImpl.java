package com.E_commerce.user.service;

import com.E_commerce.user.controller.AdministratorController;
import com.E_commerce.user.model.User;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UseDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCrypt;

    @Autowired
    HttpSession session;

    private final Logger log = LoggerFactory.getLogger(UseDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("esto es el Username: {}");
        Optional<User> optionalUser=userService.findByEmail(username);
        if(optionalUser.isPresent()){
            log.info("este es el ide del user: {}", optionalUser.get().getId());
            session.setAttribute("iduser",optionalUser.get().getId());
            User user=optionalUser.get();
            return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(bCrypt.encode(user.getPassword())).roles(user.getType()).build();
        }else{
            throw new UsernameNotFoundException("usuario no encontrado");
        }

    }
}
