package com.example.usermanagement.backend.backend.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "com.example.usermanagement.backend.backend")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

}
