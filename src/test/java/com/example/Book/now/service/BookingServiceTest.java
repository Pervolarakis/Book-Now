package com.example.Book.now.service;

import com.example.Book.now.exceptions.NotPermittedException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Test
    @Transactional
    public void getBookingByIdTest(){
        Assertions.assertThrows(NotPermittedException.class, () -> bookingService.getBookingById(1, "pemanuele1@census.gov"), "Successfully block random users from getting booking they dont own");
        Assertions.assertDoesNotThrow(() -> bookingService.getBookingById(1, "kpink0@telegraph.co.uk"), "Successfully returns if user owns booking");
        Assertions.assertDoesNotThrow(() -> bookingService.getBookingById(1, "admin@mail.com"), "Successfully returns if user is admin");
    }

}
