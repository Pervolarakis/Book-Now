package com.example.Book.now.controller;

import com.example.Book.now.Entities.Vehicle;
import com.example.Book.now.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor

public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    private List<Vehicle> getAllVehicles(){
        return vehicleService.findAllVehicles();
    }



}
