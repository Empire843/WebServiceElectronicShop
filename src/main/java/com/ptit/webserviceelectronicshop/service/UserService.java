package com.ptit.webserviceelectronicshop.service;

import com.ptit.webserviceelectronicshop.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);

    List<User> getAllUsers();
    User getUserById(Long id);
}
