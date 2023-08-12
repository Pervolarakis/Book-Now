package com.example.Book.now.responseBodies;

import com.example.Book.now.Entities.StoreLocation;
import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.Entities.Vehicle;

import java.util.Date;

public record BookingDTO(
        Integer bookingId,
        Date bookingCreationDate,
        VehicleDTO vehicleId,
        Integer quantity,
        Date pickupDate,
        Date deliveryDate,
        Integer customerId,
        Integer pickupLocationId
) {
}
