package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Booking.class)
    @JoinColumn(name = "booking_id", referencedColumnName = "bookingId", nullable = false)
    private Booking bookingId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserProfile.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserProfile userId;
    private Date reviewCreatedAt;
    private Integer rating;
    private String reviewText;

}
