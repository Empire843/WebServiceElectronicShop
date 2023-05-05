package com.ptit.webserviceelectronicshop.service.implement;

import com.ptit.webserviceelectronicshop.model.User;
import com.ptit.webserviceelectronicshop.repository.UserRepository;
import com.ptit.webserviceelectronicshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User registerUser(User user) {
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
