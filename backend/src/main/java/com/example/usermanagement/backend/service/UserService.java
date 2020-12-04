package com.example.usermanagement.backend.service;

import com.example.usermanagement.backend.domain.User;
import com.example.usermanagement.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        throw new EntityNotFoundException("User with id:" + id + " not found");
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> dbUser = userRepository.findById(user.getId());
        if (dbUser.isPresent())
            return userRepository.save(user);
        throw new EntityNotFoundException("User with id:" + user.getId() + " not found");

    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
