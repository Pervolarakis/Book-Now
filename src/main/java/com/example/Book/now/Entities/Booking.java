package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    private Integer bookingId;
    private Date bookingCreationDate;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Vehicle.class)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId", nullable = false)
    private Vehicle vehicleId;
    private Integer quantity;
    private Date pickupDate;
    private Date deliveryDate;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "userId", nullable = false)
    private UserAccount customerId;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = StoreLocation.class)
    @JoinColumn(name = "pickup_location_id", referencedColumnName = "storeId", nullable = false)
    private StoreLocation pickupLocationId;

}
