package com.example.Book.now.service;

import com.example.Book.now.Entities.Vehicle;
import com.example.Book.now.RequestBodies.CreateVehicleRequestBody;
import com.example.Book.now.RequestBodies.UpdateVehicleRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.VehicleRepository;
import com.example.Book.now.responseBodies.VehicleDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<VehicleDTO> findAllVehicles(){

        return vehicleRepository.findAll().stream().map(
                vehicle -> new VehicleDTO(
                        vehicle.getVehicleId(),
                        vehicle.getName(),
                        vehicle.getBrand(),
                        vehicle.getYear(),
                        vehicle.getVehicleType(),
                        vehicle.getNumOfSeats(),
                        vehicle.getMileage(),
                        vehicle.getTransmission(),
                        vehicle.getFuel(),
                        vehicle.getNumOfBags(),
                        vehicle.getNumOfDoors(),
                        vehicle.getAc(),
                        vehicle.getPhoto()
                )).collect(Collectors.toList());
    }

    public VehicleDTO findVehicleById(Integer id) throws ResourceNotFoundException {
        return vehicleRepository.findById(id).map(
                vehicle -> new VehicleDTO(
                        vehicle.getVehicleId(),
                        vehicle.getName(),
                        vehicle.getBrand(),
                        vehicle.getYear(),
                        vehicle.getVehicleType(),
                        vehicle.getNumOfSeats(),
                        vehicle.getMileage(),
                        vehicle.getTransmission(),
                        vehicle.getFuel(),
                        vehicle.getNumOfBags(),
                        vehicle.getNumOfDoors(),
                        vehicle.getAc(),
                        vehicle.getPhoto()
        )).orElseThrow(() -> new ResourceNotFoundException("Vehicle"));

    }

    public Integer createNewVehicle(CreateVehicleRequestBody vehicleRequestBody){
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleRequestBody.getName());
        vehicle.setBrand(vehicleRequestBody.getBrand());
        vehicle.setYear(vehicleRequestBody.getYear());
        vehicle.setVehicleType(vehicleRequestBody.getVehicleType());
        vehicle.setNumOfSeats(vehicleRequestBody.getNumOfSeats());
        vehicle.setMileage(vehicleRequestBody.getMileage());
        vehicle.setTransmission(vehicleRequestBody.getTransmission());
        vehicle.setFuel(vehicleRequestBody.getFuel());
        vehicle.setNumOfBags(vehicleRequestBody.getNumOfBags());
        vehicle.setNumOfDoors(vehicleRequestBody.getNumOfDoors());
        vehicle.setAc(vehicleRequestBody.getAc());
        vehicle.setPhoto(vehicleRequestBody.getPhoto());
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return savedVehicle.getVehicleId();
    }

    public Integer updateVehicleById(UpdateVehicleRequestBody updateVehicleRequestBody){
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(updateVehicleRequestBody.getId());
        vehicle.setName(updateVehicleRequestBody.getName());
        vehicle.setBrand(updateVehicleRequestBody.getBrand());
        vehicle.setYear(updateVehicleRequestBody.getYear());
        vehicle.setVehicleType(updateVehicleRequestBody.getVehicleType());
        vehicle.setNumOfSeats(updateVehicleRequestBody.getNumOfSeats());
        vehicle.setMileage(updateVehicleRequestBody.getMileage());
        vehicle.setTransmission(updateVehicleRequestBody.getTransmission());
        vehicle.setFuel(updateVehicleRequestBody.getFuel());
        vehicle.setNumOfBags(updateVehicleRequestBody.getNumOfBags());
        vehicle.setNumOfDoors(updateVehicleRequestBody.getNumOfDoors());
        vehicle.setAc(updateVehicleRequestBody.getAc());
        vehicle.setPhoto(updateVehicleRequestBody.getPhoto());
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return savedVehicle.getVehicleId();
    }
}
