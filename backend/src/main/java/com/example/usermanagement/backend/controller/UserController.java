package com.example.usermanagement.backend.controller;

import com.example.usermanagement.backend.domain.User;
import com.example.usermanagement.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @GetMapping
    public List<User> getUserList() {
        return userService.getUsers();
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUserById(id);
    }


}
