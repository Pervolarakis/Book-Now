package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Vehicle.class)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId", nullable = false)
    private Integer vehicleId;
    private Integer quantity;

    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = StoreLocation.class)
    @JoinColumn(name = "store_id", referencedColumnName = "storeId", nullable = false)
    private Integer storeId;
}
