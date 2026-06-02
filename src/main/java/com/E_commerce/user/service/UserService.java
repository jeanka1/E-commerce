package com.E_commerce.user.service;

import com.E_commerce.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Integer id);
    User save (User user);
    Optional<User> findByEmail(String email);

    List<User> findAll();

}
