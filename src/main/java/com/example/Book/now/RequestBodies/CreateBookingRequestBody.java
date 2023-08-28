package com.example.Book.now.RequestBodies;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateBookingRequestBody {
    @NotNull(message = "Please provide a vehicle id")
    private Integer vehicleId;
    @NotNull(message = "Please provide a valid quantity")
    @Min(value = 1, message = "Quantity should be bigger than 1")
    private Integer quantity;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date pickupDate;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date deliveryDate;
    @NotNull(message = "Please provide a store id")
    private Integer pickupLocationId;
    private String coupon;
}
