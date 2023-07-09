package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestBody {

    @NotBlank(message = "Username cannot be empty!")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    private String password;

}
