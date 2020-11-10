package com.example.backend.domain;

import com.example.backend.BackendApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Period;

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
    public void setAgeOnGetAgeWhenBirthdayIsSet() {
        LocalDate birthday = LocalDate.of(1996, 8, 14);
        user.setBirthday(birthday);

        Assert.assertEquals(Period.between(birthday, LocalDate.now()).getYears(), user.getAge());
    }

    @Test
    public void setFullNameOnGetFullNameWhenFirstNameAndLastNameAreSets() {

        user.setBirthday(LocalDate.of(1996, 8, 14));
        user.setFirstName("James");
        user.setLastName("Brandan");


        Assert.assertEquals(user.getFirstName() + " " + user.getLastName(), user.getFullName());
    }

}
