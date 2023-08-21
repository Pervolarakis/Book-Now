package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateBookingRequestBody;
import com.example.Book.now.RequestBodies.CreateStoreLocationRequestBody;
import com.example.Book.now.RequestBodies.UpdateStoreLocationRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.StoreLocationDTO;
import com.example.Book.now.service.StoreLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/location")
public class StoreLocationController {

    private final StoreLocationService storeLocationService;

    @GetMapping("/{locationId}")
    public ResponseEntity<StoreLocationDTO> getStoreLocationById(@PathVariable Integer locationId) throws ResourceNotFoundException {
        return ResponseEntity.ok(storeLocationService.getStoreLocationById(locationId));
    }

    @GetMapping
    public ResponseEntity<List<StoreLocationDTO>> getAllStoreLocations(){
        return ResponseEntity.ok(storeLocationService.getAllStoreLocations());
    }

    @PostMapping
    public ResponseEntity<Integer> createStoreLocation(@Valid @RequestBody CreateStoreLocationRequestBody createStoreLocationRequestBody){
        return new ResponseEntity(storeLocationService.createStoreLocation(createStoreLocationRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<Integer> updateStoreLocation(@PathVariable Integer locationId, @RequestBody UpdateStoreLocationRequestBody updateStoreLocationRequestBody) throws ResourceNotFoundException {
        return ResponseEntity.ok(storeLocationService.updateStoreLocation(updateStoreLocationRequestBody, locationId));
    }
}
