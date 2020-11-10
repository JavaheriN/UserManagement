package com.example.backend.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

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
    @NotNull
    private LocalDate birthday;
    private Gender gender;
    @Transient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int age;

    public int getAge() {

        Period period = Period.between(birthday, LocalDate.now());
        age = period.getYears();
        return age;

    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
