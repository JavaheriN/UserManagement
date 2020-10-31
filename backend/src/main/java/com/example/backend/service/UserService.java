package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }
    public Page<User> getUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void createUser(User user){
        userRepository.save(user);
    }
    public void updateUser(int id,User user){
        userRepository.save(user);
    }
    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }
}
