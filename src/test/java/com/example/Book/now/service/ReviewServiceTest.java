package com.example.Book.now.service;

import com.example.Book.now.RequestBodies.CreateReviewRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.ReviewRepository;
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
    @Autowired
    private ReviewRepository reviewRepository;

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

    @Test
    @Transactional
    public void createReviewTest() throws NotPermittedException, ResourceNotFoundException {
        CreateReviewRequestBody createReviewRequestBody = new CreateReviewRequestBody();
        createReviewRequestBody.setReviewText("very nice!");
        createReviewRequestBody.setRating(3);
        createReviewRequestBody.setBookingId(100);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> reviewService.createReview(createReviewRequestBody, "kpink0@telegraph.co.uk"), "Throws if booking doesnt exist");
        createReviewRequestBody.setBookingId(1);
        Assertions.assertThrows(NotPermittedException.class, () -> reviewService.createReview(createReviewRequestBody, "pemanuele1@census.gov"), "Throws if user doesnt own the booking");
        Integer reviewId =  reviewService.createReview(createReviewRequestBody, "kpink0@telegraph.co.uk");
        Assertions.assertNotNull(reviewRepository.findById(reviewId), "Review is successfully created");
    }
}
