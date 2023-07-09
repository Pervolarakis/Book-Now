package com.example.Book.now.Entities;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Date;

public class UserProfile {

    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String state;
    private String city;
    private Date dateOfBirth;

}
