package com.example.usermanagement.backend.controller;

import com.example.usermanagement.backend.domain.User;
import com.example.usermanagement.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

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

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    void shouldFetchOneUserById() throws Exception {

        User user = userList.get(0);

        given(userService.findUserById(user.getId())).willReturn(user);

        this.mockMvc.perform(get("/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.address", is(user.getAddress())));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
