package com.example.Book.now.service;

import com.example.Book.now.RequestBodies.BookingStatusRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.BookingStatusDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookingStatusServiceTest {

    @Autowired
    private BookingStatusService bookingStatusService;

    @Test
    @Transactional
    public void getBookingStatusByBookingIdTest() throws NotPermittedException, ResourceNotFoundException {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingStatusService.getBookingStatusByBookingId(1, "aaa@mail.com"), "Successfully throws if user doesnt exist");
        List<BookingStatusDTO> listBooking = bookingStatusService.getBookingStatusByBookingId(55, "admin@mail.com");
        Assertions.assertEquals(listBooking.size(), 0);
        Assertions.assertThrows(NotPermittedException.class, () -> bookingStatusService.getBookingStatusByBookingId(1, "pemanuele1@census.gov"), "Throws if user doesnt own the booking or isnt admin");
        Assertions.assertDoesNotThrow(() -> bookingStatusService.getBookingStatusByBookingId(1, "kpink0@telegraph.co.uk"), "Doesnt throw if user owns the booking");
        Assertions.assertDoesNotThrow(() -> bookingStatusService.getBookingStatusByBookingId(1, "admin@mail.com"), "Doesnt throw if user is admin");
        List<BookingStatusDTO> bookingStatusList = bookingStatusService.getBookingStatusByBookingId(1, "kpink0@telegraph.co.uk");
        Assertions.assertEquals(bookingStatusList.size(), 2, "Successfully returns all bookings status");
    }

    @Test
    @Transactional
    public void createBookingStatusTest() throws NotPermittedException, ResourceNotFoundException {
        BookingStatusRequestBody bookingStatusRequestBody = new BookingStatusRequestBody();
        bookingStatusRequestBody.setStatus("test-status");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingStatusService.createBookingStatus(55, bookingStatusRequestBody), "Throws if booking doesnt exist");
        Assertions.assertDoesNotThrow(() -> bookingStatusService.createBookingStatus(1, bookingStatusRequestBody), "Doesnt throw if booking id is valid");
        List<BookingStatusDTO> bookingStatusList = bookingStatusService.getBookingStatusByBookingId(1, "admin@mail.com");
        Assertions.assertEquals(bookingStatusList.size(), 3, "New status is successfully saved");
        Assertions.assertEquals(bookingStatusList.get(bookingStatusList.size()-1).status(), "test-status", "New status is successfully saved");
    }
}
