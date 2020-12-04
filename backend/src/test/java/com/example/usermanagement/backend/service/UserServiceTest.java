package com.example.usermanagement.backend.service;

import com.example.usermanagement.backend.domain.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "com.example.usermanagement.backend")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserSuccessfully() {

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

    @Test
    public void getUserSuccessfully() {

        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Brandan");
        userService.createUser(user);

        User dbUser = userService.findUserById(user.getId());

        Assert.assertNotNull(dbUser);
        Assert.assertEquals(user.getFirstName(), dbUser.getFirstName());
        Assert.assertEquals(user.getLastName(), dbUser.getLastName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void getUserShouldFailWhenUserNotExist() {
        userService.findUserById(1);
    }

    @Test
    public void updateUserSuccessfully() {

        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Brandan");
        userService.createUser(user);

        user.setFirstName("Tom");
        user.setLastName("Hombergs");
        userService.updateUser(user);
        User dbUserAfterUpdate = userService.findUserById(user.getId());

        Assert.assertEquals(user.getFirstName(), dbUserAfterUpdate.getFirstName());
        Assert.assertEquals(user.getLastName(), dbUserAfterUpdate.getLastName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void updatingShouldFailWhenIdNotExist() {
        User user = new User();
        user.setId(1);

        userService.updateUser(user);

    }

    @Test
    public void deleteUserSuccessfully() {

        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Brandan");
       User createdUser=  userService.createUser(user);

        userService.deleteUserById(user.getId());
        List<User> userList = userService.getUsers();

        Assert.assertNotNull(createdUser);
        Assert.assertEquals(userList.size(),0);
    }

  




}
