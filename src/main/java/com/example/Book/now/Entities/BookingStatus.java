package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class BookingStatus {
    @Id
    private Integer bookingStatusId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Booking.class)
    @JoinColumn(name = "booking_id", referencedColumnName = "bookingId", nullable = false)
    private Integer bookingId;
    private Date statusDate;
    private String status;

}
