package com.example.Book.now.service;

import com.example.Book.now.Entities.Vehicle;
import com.example.Book.now.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> findAllVehicles(){
        return vehicleRepository.findAll();
    }

}
