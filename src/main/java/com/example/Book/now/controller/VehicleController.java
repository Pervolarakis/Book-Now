package com.example.Book.now.controller;

import com.example.Book.now.Entities.Vehicle;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.VehicleDTO;
import com.example.Book.now.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor

public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public List<VehicleDTO> getAllVehicles(){
        return vehicleService.findAllVehicles();
    }

    @GetMapping("/vehicle")
    public VehicleDTO getVehicleById(@RequestParam Integer vehicleId) throws ResourceNotFoundException {
        return vehicleService.findVehicleById(vehicleId);
    }



}
