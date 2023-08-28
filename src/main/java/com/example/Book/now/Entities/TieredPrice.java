package com.example.Book.now.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TieredPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tieredPriceId;
    private Integer durationInDays;
    private Float discountPercentage;

    private Boolean isActive;

}
