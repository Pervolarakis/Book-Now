package com.example.Book.now.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TieredPricing {

    @Id
    private Integer tieredPriceId;
    private Integer durationInDays;
    private Float discountPercentage;

}
