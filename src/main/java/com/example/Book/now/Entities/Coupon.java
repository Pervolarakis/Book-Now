package com.example.Book.now.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Coupon {

    @Id
    private String couponId;
    private Float discountPercentage;
    private Date expiresAt;
    private Boolean expired;

}
