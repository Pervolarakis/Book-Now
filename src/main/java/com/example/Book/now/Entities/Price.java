package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Price {

    @Id
    private Integer priceId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Vehicle.class)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId", nullable = false)
    private Vehicle vehicleId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = StoreLocation.class)
    @JoinColumn(name = "store_id", referencedColumnName = "storeId", nullable = false)
    private StoreLocation storeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Float price;

}
