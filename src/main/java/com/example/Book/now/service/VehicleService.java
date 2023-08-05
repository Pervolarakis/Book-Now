package com.example.Book.now.service;

import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.VehicleRepository;
import com.example.Book.now.responseBodies.VehicleDTO;
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

}
