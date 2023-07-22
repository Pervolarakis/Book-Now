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
    private Integer id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserAccount userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String state;
    private String city;
    private Date dateOfBirth;

}
