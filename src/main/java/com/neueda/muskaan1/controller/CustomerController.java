package com.neueda.muskaan1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private MongoRepository mongoRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return mongoRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return mongoRepository.findById(id).orElse(null);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return mongoRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        User existingUser = mongoRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());

            return mongoRepository.save(existingUser);
        }
        return null;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        mongoRepository.deleteById(id);
    }
}