package com.example.Book.now.repository;

import com.example.Book.now.Entities.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatus, Integer> {
    List<BookingStatus> findByBookingIdBookingIdOrderByStatusDate(Integer bookingId);
}
