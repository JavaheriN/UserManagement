package com.example.backend.domain;

import com.example.backend.BackendApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class UserValidationTest {

    private Validator validator;

    @Before
    public void beforeTest() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void emailValidationTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Brandan");

        user.setEmailAddress("test");
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertTrue(
                violations.stream()
                        .filter(x -> x.getMessage()
                                .contains("email")).count() > 0);
    }

    @Test
    public void nameValidationTest() {
        User user = new User();
        user.setId(1);
        user.setLastName("Brandan");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertTrue(
                violations.stream()
                        .filter(x -> x.getMessage().contains("null")).count() > 0);
    }
    @Test
    public void lastNameValidationTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("James");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertTrue(
                violations.stream()
                        .filter(x -> x.getMessage().contains("null")).count() > 0);
    }

}
