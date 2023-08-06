package com.example.Book.now.RequestBodies;

import com.example.Book.now.Entities.FuelType;
import com.example.Book.now.Entities.TransmissionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVehicleRequestBody {
    private Integer id;
    private String name;
    private String brand;
    private Integer year;
    private String vehicleType;
    private Integer numOfSeats;
    private Integer mileage;
    private TransmissionType transmission;
    private FuelType fuel;
    private Integer numOfBags;
    private Integer numOfDoors;
    private Boolean ac;
    private String photo;
}
