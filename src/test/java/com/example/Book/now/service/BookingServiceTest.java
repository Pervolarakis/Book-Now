package com.example.Book.now.service;

import com.example.Book.now.Entities.Booking;
import com.example.Book.now.RequestBodies.CreateBookingRequestBody;
import com.example.Book.now.RequestBodies.UpdateBookingRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.exceptions.UserDoesntExistsException;
import com.example.Book.now.exceptions.VehicleNotAvailableException;
import com.example.Book.now.responseBodies.BookingDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Test
    @Transactional
    public void getBookingByIdTest() throws NotPermittedException, ResourceNotFoundException {
        Assertions.assertThrows(NotPermittedException.class, () -> bookingService.getBookingById(1, "pemanuele1@census.gov"), "Successfully block random users from getting booking they dont own");
        Assertions.assertNotNull(bookingService.getBookingById(1, "kpink0@telegraph.co.uk"), "Successfully returns if user owns booking");
        Assertions.assertNotNull(bookingService.getBookingById(1, "admin@mail.com"), "Successfully returns if user is admin");
        Assertions.assertThrows(UserDoesntExistsException.class, () -> bookingService.getBookingById(1, "aaa@mail.com"), "Fails if user doesnt exist");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingService.getBookingById(55, "admin@mail.com"), "Throws if booking doesnt exist");
    }

    @Test
    @Transactional
    public void getBookingsByLocationIdTest(){
        Assertions.assertNotNull(bookingService.getBookingsByLocationId(55), "Successfully returns bookings");
        List<BookingDTO> bookings = bookingService.getBookingsByLocationId(1);
        Assertions.assertEquals(bookings.size(), 7, "Successfully returns all bookings for location");
    }

    @Test
    @Transactional
    public void getBookingsByUserIdTest() throws NotPermittedException {
        Assertions.assertThrows(UserDoesntExistsException.class, () -> bookingService.getBookingsByUserId(1, "aaaa@mail.com"), "Throws if user doesnt exist");
        Assertions.assertThrows(NotPermittedException.class, () -> bookingService.getBookingsByUserId(1, "pemanuele1@census.gov"), "Throws if user tries to fetch bookings he doesnt own");
        Assertions.assertDoesNotThrow(() -> bookingService.getBookingsByUserId(1, "admin@mail.com"), "Doesnt throw if user tries to fetch bookings as admin");
        Assertions.assertDoesNotThrow(() -> bookingService.getBookingsByUserId(1, "kpink0@telegraph.co.uk"), "Doesnt throw if user tries to fetch bookings he owns");
        List<BookingDTO> bookings = bookingService.getBookingsByUserId(1, "kpink0@telegraph.co.uk");
        Assertions.assertEquals(bookings.size(), 3, "Successfully returns all bookings for user");
    }

    @Test
    @Transactional
    public void getAllBookingsTest(){
        List<BookingDTO> bookings = bookingService.getAllBookings();
        Assertions.assertEquals(bookings.size(), 13, "Successfully returns all bookings");
    }

    @Test
    @Transactional
    public void createBookingTest() throws VehicleNotAvailableException, ResourceNotFoundException, NotPermittedException {
        CreateBookingRequestBody createBookingRequestBody = new CreateBookingRequestBody();
        createBookingRequestBody.setPickupDate(new Date("10/02/2023"));
        createBookingRequestBody.setDeliveryDate(new Date("10/10/2023"));
        createBookingRequestBody.setQuantity(1);
        createBookingRequestBody.setVehicleId(1);
        createBookingRequestBody.setPickupLocationId(2);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingService.createBooking(createBookingRequestBody, "aaa@mail.com"), "Throws if user doesnt exist");
        createBookingRequestBody.setVehicleId(500);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingService.createBooking(createBookingRequestBody, "admin@mail.com"), "Throws if vehicle doesnt exist");
        createBookingRequestBody.setVehicleId(1);
        createBookingRequestBody.setPickupLocationId(200);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingService.createBooking(createBookingRequestBody, "admin@mail.com"), "Throws if location doesnt exist");
        createBookingRequestBody.setPickupLocationId(2);
        Assertions.assertDoesNotThrow(() -> bookingService.createBooking(createBookingRequestBody, "admin@mail.com"), "Successfully creates booking");
        Integer bookingId = bookingService.createBooking(createBookingRequestBody, "admin@mail.com");
        BookingDTO booking = bookingService.getBookingById(bookingId, "admin@mail.com");
        Assertions.assertEquals(booking.price(), 1587.87f, 0.001, "Successfully calculates and saves price");
    }

    @Test
    @Transactional
    public void updateBookingByIdTest() throws NotPermittedException, ResourceNotFoundException, VehicleNotAvailableException {
        UpdateBookingRequestBody updateBookingRequestBody = new UpdateBookingRequestBody();
        updateBookingRequestBody.setPickupDate(new Date("10/02/2023"));
        updateBookingRequestBody.setDeliveryDate(new Date("10/10/2023"));
        updateBookingRequestBody.setQuantity(1);
        updateBookingRequestBody.setVehicleId(1);
        updateBookingRequestBody.setPickupLocationId(2);
        Assertions.assertThrows(UserDoesntExistsException.class, () -> bookingService.updateBookingById(updateBookingRequestBody, 1, "aaa@mail.com"), "Throws if user doesnt exist");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingService.updateBookingById(updateBookingRequestBody, 55, "aaa@mail.com"), "Throws if booking doesnt exist");
        Assertions.assertThrows(NotPermittedException.class, () -> bookingService.updateBookingById(updateBookingRequestBody, 1, "pemanuele1@census.gov"), "Throws if user doesnt own the booking or isnt admin");
        updateBookingRequestBody.setVehicleId(55);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingService.updateBookingById(updateBookingRequestBody, 1, "kpink0@telegraph.co.uk"), "Throws if vehicle doesnt exist");
        updateBookingRequestBody.setVehicleId(1);
        updateBookingRequestBody.setPickupLocationId(55);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookingService.updateBookingById(updateBookingRequestBody, 1, "kpink0@telegraph.co.uk"), "Throws if location doesnt exist");
        updateBookingRequestBody.setPickupLocationId(1);
        Assertions.assertDoesNotThrow(() -> bookingService.updateBookingById(updateBookingRequestBody, 1, "kpink0@telegraph.co.uk"), "Doesnt throw if user owns the booking");
        Assertions.assertDoesNotThrow(() -> bookingService.updateBookingById(updateBookingRequestBody, 1, "admin@mail.com"), "Doesnt throw if user is admin");
        updateBookingRequestBody.setPickupLocationId(2);
        Integer bookingId = bookingService.updateBookingById(updateBookingRequestBody, 1,"admin@mail.com");
        BookingDTO booking = bookingService.getBookingById(bookingId, "admin@mail.com");
        Assertions.assertEquals(booking.price(), 1587.87f, 0.001, "Successfully calculates and saves price");
    }
}
