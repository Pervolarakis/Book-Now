package com.example.Book.now.RequestBodies;

import com.example.Book.now.Entities.StoreLocation;
import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.Entities.Vehicle;
import jakarta.persistence.*;
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
    private Integer quantity;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date pickupDate;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date deliveryDate;
    @NotNull(message = "Please provide a store id")
    private Integer pickupLocationId;
}
