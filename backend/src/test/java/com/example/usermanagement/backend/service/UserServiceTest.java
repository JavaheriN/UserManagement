package com.example.usermanagement.backend.service;

import com.example.usermanagement.backend.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "com.example.usermanagement.backend")
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
        Assert.assertEquals(1, dbUsers.size());
        Assert.assertEquals(1, dbUsers.stream().filter(x -> x.getFirstName().equals(user.getFirstName())).count());

    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void createUser_Name_IsNotSet_Fail() {
        User user = new User();
        user.setId(1);
        user.setLastName("Brandan");
        userService.createUser(user);

        List<User> dbUsers = userService.getUsers();

        Assert.assertEquals(0, dbUsers.size());

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
