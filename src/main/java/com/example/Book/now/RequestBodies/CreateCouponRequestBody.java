package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateCouponRequestBody {

    @NotBlank(message = "Please provide a valid coupon code")
    private String couponId;
    @NotNull(message = "Please provide a valid discount percentage")
    private Float discountPercentage;
    @NotNull(message = "Please provide a valid date")
    private Date expiresAt;

}
