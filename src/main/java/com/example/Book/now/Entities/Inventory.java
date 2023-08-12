package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    private Integer inventoryId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Vehicle.class)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId", nullable = false)
    private Vehicle vehicleId;
    private Integer quantity;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = StoreLocation.class)
    @JoinColumn(name = "store_id", referencedColumnName = "storeId", nullable = false)
    private StoreLocation storeId;
}
