package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestBody {

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    private String password;

}
