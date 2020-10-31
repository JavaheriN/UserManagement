package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getUserList() {
        return userService.getUsers();
    }

//    @GetMapping
//    public Page<User> getUserList(Pageable pageable) {
//        return userService.getUsers(pageable);
//    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id,@RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUserById(id);
    }


}
