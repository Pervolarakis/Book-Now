package com.example.Book.now.RequestBodies;

import com.example.Book.now.Entities.FuelType;
import com.example.Book.now.Entities.TransmissionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVehicleRequestBody {

    @NotBlank(message = "Vehicle name cannot be empty!")
    private String name;
    @NotBlank(message = "Vehicle brand cannot be empty!")
    private String brand;
    @NotNull(message = "Vehicle year cannot be empty!")
    private Integer year;
    @NotBlank(message = "Vehicle type cannot be empty!")
    private String vehicleType;
    @NotNull(message = "Number of seats cannot be empty!")
    private Integer numOfSeats;
    @NotNull(message = "Mileage cannot be empty!")
    private Integer mileage;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transmission type cannot be empty!")
    private TransmissionType transmission;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Fuel type cannot be empty!")
    private FuelType fuel;
    @NotNull(message = "Number or bags cannot be empty!")
    private Integer numOfBags;
    @NotNull(message = "Number or doors cannot be empty!")
    private Integer numOfDoors;
    @NotNull(message = "Ac cannot be empty!")
    private Boolean ac;
    @NotBlank(message = "Photo cannot be empty!")
    private String photo;
}
