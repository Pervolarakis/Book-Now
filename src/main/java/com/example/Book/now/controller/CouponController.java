package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateCouponRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.CouponDTO;
import com.example.Book.now.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponDTO>> getAllCoupons(){
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @PostMapping
    public ResponseEntity<String> createCoupon(@Valid @RequestBody CreateCouponRequestBody createCouponRequestBody){
        return new ResponseEntity(couponService.createCoupon(createCouponRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable String couponId) throws ResourceNotFoundException {
        return ResponseEntity.ok(couponService.deleteCoupon(couponId));
    }
}
