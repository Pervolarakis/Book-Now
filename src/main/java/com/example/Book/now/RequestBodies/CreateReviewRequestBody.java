package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewRequestBody {
    @NotNull(message="Please provide a booking Id")
    private Integer bookingId;
    @NotNull(message = "Please provide a rating")
    @Min(value = 0, message = "Minimum rating is 0")
    @Max(value = 5, message = "Maximum rating is 5")
    private Integer rating;
    @NotBlank(message = "Please provide a review text")
    private String reviewText;

}
