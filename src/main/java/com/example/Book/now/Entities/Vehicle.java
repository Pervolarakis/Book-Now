package com.example.Book.now.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleId;

    private String name;
    private String brand;
    private Integer year;
    private String vehicle_type;
    private Integer num_of_seats;
    private Integer mileage;
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionEnum;
    @Enumerated(EnumType.STRING)
    private FuelType fuelEnum;
    private Integer num_of_bags;
    private Integer num_of_doors;
    private Boolean ac;
    private String photo;


}
