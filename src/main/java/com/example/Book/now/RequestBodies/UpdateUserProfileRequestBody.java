package com.example.Book.now.RequestBodies;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateUserProfileRequestBody {
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String state;
    private String city;
    private Date dateOfBirth;
}
