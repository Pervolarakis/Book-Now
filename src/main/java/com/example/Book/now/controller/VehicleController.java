package com.example.Book.now.controller;

import com.example.Book.now.Entities.Vehicle;
import com.example.Book.now.RequestBodies.CreateVehicleRequestBody;
import com.example.Book.now.RequestBodies.UpdateVehicleRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.VehicleDTO;
import com.example.Book.now.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.findAllVehicles());
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Integer vehicleId) throws ResourceNotFoundException {
        return ResponseEntity.ok(vehicleService.findVehicleById(vehicleId));
    }

    @PostMapping
    public ResponseEntity<Integer> createVehicle(@Valid @RequestBody CreateVehicleRequestBody createVehicleRequestBody) throws ResourceNotFoundException {
        return new ResponseEntity(vehicleService.createNewVehicle(createVehicleRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Integer> updateVehicleById(@PathVariable Integer vehicleId, @RequestBody UpdateVehicleRequestBody updateVehicleRequestBody) throws ResourceNotFoundException {
        updateVehicleRequestBody.setId(vehicleId);
        return ResponseEntity.ok(vehicleService.updateVehicleById(updateVehicleRequestBody));
    }

}
