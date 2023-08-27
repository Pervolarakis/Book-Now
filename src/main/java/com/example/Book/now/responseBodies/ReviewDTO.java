package com.example.Book.now.responseBodies;

import java.util.Date;

public record ReviewDTO(
        Integer reviewId,
        Date bookingReturnDate,
        ReviewProfileDTO user,
        Date reviewCreatedAt,
        Integer rating,
        String reviewText
) {
}
