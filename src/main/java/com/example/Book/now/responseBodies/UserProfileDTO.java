package com.example.Book.now.responseBodies;

import java.util.Date;

public record UserProfileDTO(
        Integer userId,
        String firstName,
        String lastName,
        String phone,
        String country,
        String state,
        String city,
        Date dateOfBirth) {
}
