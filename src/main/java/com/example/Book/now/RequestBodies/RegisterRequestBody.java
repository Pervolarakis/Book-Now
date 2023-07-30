package com.example.Book.now.RequestBodies;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterRequestBody {

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @Size(min = 8, message = "Please provide a password longer than 8 characters")
    @NotBlank(message = "Email cannot be empty!")
    private String password;
    @NotBlank(message = "First name cannot be empty!")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;
    @NotBlank(message = "Phone cannot be empty!")
    private String phone;
    @NotBlank(message = "Country cannot be empty!")
    private String country;
    @NotBlank(message = "State cannot be empty!")
    private String state;
    @NotBlank(message = "City cannot be empty!")
    private String city;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateOfBirth;

}
