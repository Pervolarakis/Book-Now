package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateTieredPriceRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.TieredPriceDTO;
import com.example.Book.now.service.TieredPriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiered_price")
@RequiredArgsConstructor
public class TieredPriceController {

    private final TieredPriceService tieredPriceService;

    @GetMapping
    public ResponseEntity<List<TieredPriceDTO>> getAllTieredPrice(){
        return ResponseEntity.ok(tieredPriceService.getAllTieredPrice());
    }

    @PostMapping
    public ResponseEntity<Integer> createTieredPrice(@Valid @RequestBody CreateTieredPriceRequestBody createTieredPriceRequestBody){
        return new ResponseEntity(tieredPriceService.createTieredPrice(createTieredPriceRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{tieredPriceId}")
    public ResponseEntity<Integer> disableTieredPrice(@PathVariable Integer tieredPriceId) throws ResourceNotFoundException {
        return ResponseEntity.ok(tieredPriceService.disableTieredPrice(tieredPriceId));
    }

}
