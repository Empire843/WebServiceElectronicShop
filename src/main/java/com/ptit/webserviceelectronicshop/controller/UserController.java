package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.User;
import com.ptit.webserviceelectronicshop.model.request_body.RegisterUserBody;
import com.ptit.webserviceelectronicshop.model.request_body.UserDTO;
import com.ptit.webserviceelectronicshop.service.implement.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserBody body) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
//        ModelMapper mapper = new ModelMapper();
//        User user = mapper.map(body, User.class)
        User user = new User();
        user.setEmail(body.getEmail());
        user.setPassword(body.getPassword());
        user.setFull_name(body.getFull_name());
        try {
            User createdUser = userService.registerUser(user);
            response.put("message", "User registered successfully");
            response.put("user", createdUser);
        } catch (RuntimeException e) {
            error.put("message", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @RequestBody UserDTO body) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId) {
        return null;
    }
}

