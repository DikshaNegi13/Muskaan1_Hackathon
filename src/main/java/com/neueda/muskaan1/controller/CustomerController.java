package com.neueda.muskaan1.controller;

import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.exception.CustomerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
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
    public User getUserById(@PathVariable String id) throws CustomerNotFound {
        return mongoRepository.findById(id).orElseThrow(
                () -> new CustomerNotFound("User with "+ id + " not found!"));
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws CustomerAlreadyExists{
        if(mongoRepository.existsById(user.getId()))
            throw new CustomerAlreadyExists("User with "+ User.getId() + " already exists!");
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
    public void deleteUser (@PathVariable String id)throws CustomerNotFound {
        if(!mongoRepository.existsById(id))
            throw new CustomerNotFound("User with "+ id + " not found!");
        mongoRepository.deleteById(id);
    }
}