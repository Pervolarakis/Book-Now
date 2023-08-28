package com.example.Book.now.repository;

import com.example.Book.now.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBookingIdVehicleIdVehicleId(Integer vehicleId);

    List<Review> findByUserIdId(Integer userId);

    Optional<Review> findById(Integer reviewId);
}
