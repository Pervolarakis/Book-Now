package com.example.Book.now.service;

import com.example.Book.now.Entities.FuelType;
import com.example.Book.now.Entities.TransmissionType;
import com.example.Book.now.Entities.Vehicle;
import com.example.Book.now.RequestBodies.CreateVehicleRequestBody;
import com.example.Book.now.RequestBodies.UpdateVehicleRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.VehicleDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.lang.Boolean.TRUE;

@SpringBootTest
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    @Test
    @Transactional
    public void getAllVehiclesTest(){
        List<VehicleDTO> vehicles = vehicleService.findAllVehicles();
        Assertions.assertEquals(vehicles.size(), 3, "Successfully returns all vehicles");
    }

    @Test
    @Transactional
    public void findVehicleByIdTest() throws ResourceNotFoundException {
        VehicleDTO vehicle = vehicleService.findVehicleById(1);
        Assertions.assertEquals(vehicle.name(), "ZDX", "Successfully returns vehicle");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> vehicleService.findVehicleById(5), "Throws error if vehicle doesnt exist");
    }

    @Test
    @Transactional
    public void createVehicleTest(){
        CreateVehicleRequestBody vehicleRequestBody = new CreateVehicleRequestBody();
        vehicleRequestBody.setName("test-vehicle");
        vehicleRequestBody.setBrand("Acura");
        vehicleRequestBody.setYear(1999);
        vehicleRequestBody.setVehicleType("coupe");
        vehicleRequestBody.setNumOfSeats(5);
        vehicleRequestBody.setMileage(50000);
        vehicleRequestBody.setTransmission(TransmissionType.Automatic);
        vehicleRequestBody.setFuel(FuelType.Diesel);
        vehicleRequestBody.setNumOfBags(2);
        vehicleRequestBody.setNumOfDoors(5);
        vehicleRequestBody.setAc(TRUE);
        vehicleRequestBody.setPhoto("http://dummyimage.com/119x100.png/5fa2dd/ffffff");
        Integer vehicleId = vehicleService.createNewVehicle(vehicleRequestBody);
        Assertions.assertDoesNotThrow(() -> vehicleService.findVehicleById(vehicleId), "Vehicle exists");
    }

    @Test
    @Transactional
    public void updateVehicleTest() throws ResourceNotFoundException {
        UpdateVehicleRequestBody updateVehicleRequestBody = new UpdateVehicleRequestBody();
        updateVehicleRequestBody.setId(1);
        updateVehicleRequestBody.setName("test-vehicle");
        updateVehicleRequestBody.setBrand("Acura");
        updateVehicleRequestBody.setYear(1999);
        updateVehicleRequestBody.setVehicleType("coupe");
        updateVehicleRequestBody.setNumOfSeats(5);
        updateVehicleRequestBody.setMileage(50000);
        updateVehicleRequestBody.setTransmission(TransmissionType.Automatic);
        updateVehicleRequestBody.setFuel(FuelType.Diesel);
        updateVehicleRequestBody.setNumOfBags(2);
        updateVehicleRequestBody.setNumOfDoors(5);
        updateVehicleRequestBody.setAc(TRUE);
        updateVehicleRequestBody.setPhoto("http://dummyimage.com/119x100.png/5fa2dd/ffffff");
        Integer vehicleId = vehicleService.updateVehicleById(updateVehicleRequestBody);
        Assertions.assertDoesNotThrow(() -> vehicleService.findVehicleById(vehicleId), "Vehicle exists");
        VehicleDTO vehicleDTO = vehicleService.findVehicleById(vehicleId);
        Assertions.assertEquals("test-vehicle", vehicleDTO.name(), "Vehicle successfully updated!");
    }
}
