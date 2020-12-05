package com.example.usermanagement.backend.controller;

import com.example.usermanagement.backend.domain.User;
import com.example.usermanagement.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<User> userList;


    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("James");
        user1.setLastName("Brandan");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Tom");
        user2.setLastName("Hombergs");

        this.userList.add(user1);
        this.userList.add(user2);

    }

    @Test
    void shouldFetchAllUsers() throws Exception {
        given(userService.getUsers()).willReturn(userList);

        this.mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    void shouldFetchOneUserById() throws Exception {

        User user = userList.get(0);

        given(userService.findUserById(user.getId())).willReturn(user);

        this.mockMvc.perform(get("/api/v1/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.address", is(user.getAddress())));
    }

    @Test
    void shouldReturnStatus404WhenUserNotFindById() throws Exception {

        int userId = 5;

        given(userService.findUserById(userId)).willReturn(null);

        this.mockMvc.perform(get("/api/v1/users/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateUser() throws Exception {

        given(userService.createUser(any(User.class))).willAnswer((answer -> answer.getArgument(0)));
        User user = new User();
        user.setId(2);
        user.setFirstName("Taylor");
        user.setLastName("Smith");

        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }


    @Test
    void shouldFailCreateUserWhenNameIsNull() throws Exception {

        User user = new User();
        user.setId(2);
        user.setLastName("Smith");

        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateUser() throws Exception {

        User user = new User();
        user.setId(1);
        user.setFirstName("Taylor");
        user.setLastName("Smith");
        given(userService.findUserById(user.getId())).willReturn(user);
        given(userService.updateUser(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/api/v1/users/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

    }

    @Test
    void shouldDeleteUser() throws Exception {
        User user = userList.get(0);
        doNothing().when(userService).deleteUserById(user.getId());

        this.mockMvc.perform(delete("/api/v1/users/{id}", user.getId()))
                .andExpect(status().isOk());
    }
}
