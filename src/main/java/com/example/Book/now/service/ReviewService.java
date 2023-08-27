package com.example.Book.now.service;

import com.example.Book.now.repository.ReviewRepository;
import com.example.Book.now.responseBodies.ReviewDTO;
import com.example.Book.now.responseBodies.ReviewProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDTO> findReviewsByUserId(Integer userId){
        return reviewRepository.findByUserIdId(userId).stream().map(
                review -> new ReviewDTO(
                        review.getReviewId(),
                        review.getBookingId().getDeliveryDate(),
                        new ReviewProfileDTO(
                                review.getUserId().getFirstName(),
                                review.getUserId().getLastName()
                        ),
                        review.getReviewCreatedAt(),
                        review.getRating(),
                        review.getReviewText()
                )
        ).collect(Collectors.toList());
    }

    public List<ReviewDTO> findReviewByVehicleId(Integer vehicleId){
        return reviewRepository.findByBookingIdVehicleIdVehicleId(vehicleId).stream().map(
                review -> new ReviewDTO(
                        review.getReviewId(),
                        review.getBookingId().getDeliveryDate(),
                        new ReviewProfileDTO(
                                review.getUserId().getFirstName(),
                                review.getUserId().getLastName()
                        ),
                        review.getReviewCreatedAt(),
                        review.getRating(),
                        review.getReviewText()
                )
        ).collect(Collectors.toList());
    }

}
