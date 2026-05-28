package com.E_commerce.user.service;

import com.E_commerce.user.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findAllId(Integer id);
    User save (User user);
    Optional<User> findByEmail(String email);
}
