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
    private Integer vehicleYear;
    private String vehicleType;
    private Integer numOfSeats;
    private Integer mileage;
    @Enumerated(EnumType.STRING)
    private TransmissionType transmission;
    @Enumerated(EnumType.STRING)
    private FuelType fuel;
    private Integer numOfBags;
    private Integer numOfDoors;
    private Boolean ac;
    private String photo;


}
