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
    private Integer reviewId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Booking.class)
    @JoinColumn(name = "booking_id", referencedColumnName = "bookingId", nullable = false)
    private Integer bookingId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private Integer userId;
    private Date reviewCreatedAt;
    private Integer rating;
    private String reviewText;

}
