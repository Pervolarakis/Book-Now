package com.example.Book.now.responseBodies;

import com.example.Book.now.Entities.Booking;

import java.util.Date;

public record BookingStatusDTO(
        Integer bookingStatusId,
        Booking bookingId,
        Date statusDate,
        String status
) {
}
