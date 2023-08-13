package com.example.Book.now.controller;

import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.BookingDTO;
import com.example.Book.now.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Integer bookingId, Authentication authentication) throws ResourceNotFoundException, NotPermittedException {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId, authentication.getName()));
    }

}
