package com.example.usermanagement.backend.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    public static final long serialVersionUID = 354354365L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String emailAddress;
    private String address;
    private String phoneNumber;
    private Gender gender;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
