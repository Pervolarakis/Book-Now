package com.example.Book.now.service;

import com.example.Book.now.Entities.Booking;
import com.example.Book.now.Entities.Review;
import com.example.Book.now.Entities.UserProfile;
import com.example.Book.now.RequestBodies.CreateReviewRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.exceptions.UserDoesntExistsException;
import com.example.Book.now.repository.BookingRepository;
import com.example.Book.now.repository.ReviewRepository;
import com.example.Book.now.repository.UserProfileRepository;
import com.example.Book.now.responseBodies.ReviewDTO;
import com.example.Book.now.responseBodies.ReviewProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final UserProfileRepository userProfileRepository;

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

    public Integer createReview(CreateReviewRequestBody createReviewRequestBody, String userEmail) throws ResourceNotFoundException, NotPermittedException {
        UserProfile userProfile = userProfileRepository.findByUserIdEmail(userEmail)
            .orElseThrow(() -> new UserDoesntExistsException());
        Booking booking = bookingRepository.findById(createReviewRequestBody.getBookingId())
            .orElseThrow(() -> new ResourceNotFoundException("Booking"));

        if (!booking.getCustomerId().getEmail().equals(userEmail)){
            throw new NotPermittedException();
        }
        Review review = new Review();
        review.setReviewCreatedAt(new Date());
        review.setReviewText(createReviewRequestBody.getReviewText());
        review.setRating(createReviewRequestBody.getRating());
        review.setBookingId(booking);
        review.setUserId(userProfile);
        Review savedReview = reviewRepository.save(review);
        return savedReview.getReviewId();
    }
}
