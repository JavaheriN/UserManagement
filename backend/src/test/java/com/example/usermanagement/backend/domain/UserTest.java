package com.example.usermanagement.backend.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = User.class)
public class UserTest {

    @Test
    public void setFullNameWhenFirstNameAndLastNameAreSets() {

        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Brandan");

        Assert.assertEquals(user.getFirstName() + " " + user.getLastName(), user.getFullName());
    }

}
