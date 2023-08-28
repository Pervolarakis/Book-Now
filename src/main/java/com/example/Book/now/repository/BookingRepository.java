package com.example.Book.now.repository;

import com.example.Book.now.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Optional<Booking> findById(Integer bookingId);
    List<Booking> findAll();
    List<Booking> findByPickupLocationIdStoreId(Integer storeId);
    List<Booking> findByCustomerIdUserId(Integer userId);
}
