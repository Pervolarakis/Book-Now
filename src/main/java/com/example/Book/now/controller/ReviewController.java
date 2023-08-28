package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateReviewRequestBody;
import com.example.Book.now.RequestBodies.UpdateReviewRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.ReviewDTO;
import com.example.Book.now.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByVehicleId(@PathVariable Integer vehicleId){
        return ResponseEntity.ok(reviewService.findReviewByVehicleId(vehicleId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(reviewService.findReviewsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Integer> createReview(@Valid @RequestBody CreateReviewRequestBody createReviewRequestBody, Authentication authentication) throws NotPermittedException, ResourceNotFoundException {
        return new ResponseEntity(reviewService.createReview(createReviewRequestBody, authentication.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Integer> updateReview(@PathVariable Integer reviewId, @Valid @RequestBody UpdateReviewRequestBody updateReviewRequestBody, Authentication authentication) throws NotPermittedException, ResourceNotFoundException {
        return ResponseEntity.ok(reviewService.updateReview(updateReviewRequestBody, authentication.getName(), reviewId));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer reviewId, Authentication authentication) throws NotPermittedException, ResourceNotFoundException {
        return ResponseEntity.ok(reviewService.deleteReview(authentication.getName(), reviewId));
    }

}
