package com.example.backend.service;

import com.example.backend.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "com.example.backend")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser_Success() {

        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Brandan");
        userService.createUser(user);

        List<User> dbUsers = userService.getUsers();

        Assert.assertNotNull(dbUsers);
        Assert.assertTrue(dbUsers.size() == 1);
        Assert.assertTrue(dbUsers.stream().filter(x -> x.getFirstName() == user.getFirstName()).count() == 1);

    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void createUser_Name_IsNotSet_Fail() {
        User user = new User();
        user.setId(1);
        user.setLastName("Brandan");
        userService.createUser(user);

        List<User> dbUsers = userService.getUsers();

        Assert.assertTrue(dbUsers.size() == 0);

    }

    @Test
    public void getUser_Success() {

        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Brandan");
        userService.createUser(user);

        User dbUser = userService.getUserById(user.getId());

        Assert.assertNotNull(dbUser);
        Assert.assertEquals(user.getFirstName(), dbUser.getFirstName());
        Assert.assertEquals(user.getLastName(), dbUser.getLastName());

    }
}
