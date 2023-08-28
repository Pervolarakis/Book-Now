package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReviewRequestBody {
    @Min(value = 0, message = "Minimum rating is 0")
    @Max(value = 5, message = "Maximum rating is 5")
    private Integer rating;
    private String reviewText;
}
