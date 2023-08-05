package com.example.Book.now.responseBodies;

import com.example.Book.now.Entities.FuelType;
import com.example.Book.now.Entities.TransmissionType;

public record VehicleDTO(
        Integer vehicleId,
        String name,
        String brand,
        Integer year,
        String vehicleType,
        Integer numOfSeats,
        Integer mileage,
        TransmissionType transmission,
        FuelType fuel,
        Integer numOfBags,
        Integer numOfDoors,
        Boolean ac,
        String photo){
}
