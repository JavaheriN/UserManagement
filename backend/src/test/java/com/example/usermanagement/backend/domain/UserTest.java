package com.example.usermanagement.backend.domain;

import com.example.usermanagement.backend.BackendApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class UserTest {

    private User user;

    @Before
    public void beforeTest() {
        user = new User();
        user.setId(1);
    }


    @Test
    public void setFullNameOnGetFullNameWhenFirstNameAndLastNameAreSets() {

        user.setFirstName("James");
        user.setLastName("Brandan");


        Assert.assertEquals(user.getFirstName() + " " + user.getLastName(), user.getFullName());
    }

}
