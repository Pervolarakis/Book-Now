package com.example.Book.now.service;

import com.example.Book.now.RequestBodies.CreateReviewRequestBody;
import com.example.Book.now.RequestBodies.UpdateReviewRequestBody;
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

    @Test
    @Transactional
    public void updateReviewTest() throws NotPermittedException, ResourceNotFoundException {
        UpdateReviewRequestBody updateReviewRequestBody = new UpdateReviewRequestBody();
        updateReviewRequestBody.setReviewText("updated-review");
        updateReviewRequestBody.setRating(5);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> reviewService.updateReview(updateReviewRequestBody, "kpink0@telegraph.co.uk", 55), "Throws if review doesnt exist");
        Assertions.assertThrows(NotPermittedException.class, () -> reviewService.updateReview(updateReviewRequestBody, "pemanuele1@census.gov", 1), "Throws if user doesnt own review");
        Assertions.assertDoesNotThrow(() -> reviewService.updateReview(updateReviewRequestBody, "kpink0@telegraph.co.uk", 1), "Does not throw if user owns the review and review exists");
        Integer reviewId = reviewService.updateReview(updateReviewRequestBody, "kpink0@telegraph.co.uk", 1);
        Assertions.assertEquals(reviewRepository.findById(reviewId).get().getReviewText(), "updated-review", "Review successfully updated");
    }

    @Test
    @Transactional
    public void deleteReviewTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> reviewService.deleteReview("kpink0@telegraph.co.uk", 55), "Throws if booking doesnt exist");
        Assertions.assertThrows(NotPermittedException.class, () -> reviewService.deleteReview("pemanuele1@census.gov", 1), "Throws if user doesnt own review");
        Assertions.assertDoesNotThrow(() -> reviewService.deleteReview("kpink0@telegraph.co.uk", 1), "Does not throw if user owns the review and review exists");
        Assertions.assertEquals(reviewRepository.findById(1).isPresent(), false, "Review is successfully deleted");

    }
}
