package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTieredPriceRequestBody {
    @NotNull(message = "Please provide a valid number of days")
    private Integer durationInDays;
    @NotNull(message = "Please provide a valid discount percentage")
    private Float discountPercentage;
}
