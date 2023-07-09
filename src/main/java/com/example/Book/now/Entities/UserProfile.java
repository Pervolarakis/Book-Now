package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class UserProfile {

    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String state;
    private String city;
    private Date dateOfBirth;

}
