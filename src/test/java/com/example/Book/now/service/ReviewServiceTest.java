package com.example.Book.now.service;

import com.example.Book.now.responseBodies.ReviewDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    @Transactional
    public void getReviewsByVehicleIdTest(){
        List<ReviewDTO> reviewDTO = reviewService.findReviewByVehicleId(1);
        Assertions.assertEquals(reviewDTO.size(), 4, "Successfully returns all reviews for vehicle");
        List<ReviewDTO> reviewDTO2 = reviewService.findReviewByVehicleId(2);
        Assertions.assertEquals(reviewDTO2.size(), 3, "Successfully returns all reviews for vehicle");
        List<ReviewDTO> reviewDTO3 = reviewService.findReviewByVehicleId(55);
        Assertions.assertEquals(reviewDTO3.size(), 0, "Successfully returns all reviews for vehicle");
    }

    @Test
    @Transactional
    public void getReviewsByUserId(){
        List<ReviewDTO> reviewDTO = reviewService.findReviewsByUserId(1);
        Assertions.assertEquals(reviewDTO.size(), 3, "Successfully returns all reviews for user");
        List<ReviewDTO> reviewDTO2 = reviewService.findReviewsByUserId(4);
        Assertions.assertEquals(reviewDTO2.size(), 2, "Successfully returns all reviews for user");
        List<ReviewDTO> reviewDTO3 = reviewService.findReviewsByUserId(55);
        Assertions.assertEquals(reviewDTO3.size(), 0, "Successfully returns all reviews for user");
    }
}
