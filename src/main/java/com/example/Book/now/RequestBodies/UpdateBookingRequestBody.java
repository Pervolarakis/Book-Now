package com.example.Book.now.RequestBodies;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateBookingRequestBody {

    private Integer vehicleId;
    private Integer quantity;
    private Date pickupDate;
    private Date deliveryDate;
    private Integer pickupLocationId;
}
