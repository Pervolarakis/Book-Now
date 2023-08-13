package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateBookingRequestBody;
import com.example.Book.now.RequestBodies.UpdateBookingRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.BookingDTO;
import com.example.Book.now.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Integer bookingId, Authentication authentication) throws ResourceNotFoundException, NotPermittedException {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId, authentication.getName()));
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<BookingDTO>> getBookingByLocationId(@PathVariable Integer locationId){
        return ResponseEntity.ok(bookingService.getBookingsByLocationId(locationId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingByUserId(@PathVariable Integer userId, Authentication authentication) throws NotPermittedException {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId, authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() throws NotPermittedException {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping
    public ResponseEntity<Integer> createBooking(@Valid @RequestBody CreateBookingRequestBody createBookingRequestBody, Authentication authentication) throws ResourceNotFoundException {
        return new ResponseEntity(bookingService.createBooking(createBookingRequestBody, authentication.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Integer> updateBooking(@PathVariable Integer bookingId, @RequestBody UpdateBookingRequestBody updateBookingRequestBody, Authentication authentication) throws NotPermittedException, ResourceNotFoundException {
        return ResponseEntity.ok(bookingService.updateBookingById(updateBookingRequestBody, bookingId, authentication.getName()));
    }
}
