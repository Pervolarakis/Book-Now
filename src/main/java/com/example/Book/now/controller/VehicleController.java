package com.example.Book.now.controller;

import com.example.Book.now.Entities.Vehicle;
import com.example.Book.now.RequestBodies.CreateVehicleRequestBody;
import com.example.Book.now.RequestBodies.UpdateVehicleRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.VehicleDTO;
import com.example.Book.now.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{vehicleId}")
    public VehicleDTO getVehicleById(@PathVariable Integer vehicleId) throws ResourceNotFoundException {
        return vehicleService.findVehicleById(vehicleId);
    }

    @PostMapping
    public Integer createVehicle(@Valid @RequestBody CreateVehicleRequestBody createVehicleRequestBody) throws ResourceNotFoundException {
        return vehicleService.createNewVehicle(createVehicleRequestBody);
    }

    @PutMapping("/{vehicleId}")
    public Integer updateVehicleById(@PathVariable Integer vehicleId, @RequestBody UpdateVehicleRequestBody updateVehicleRequestBody) throws ResourceNotFoundException {
        updateVehicleRequestBody.setId(vehicleId);
        return vehicleService.updateVehicleById(updateVehicleRequestBody);
    }

}
