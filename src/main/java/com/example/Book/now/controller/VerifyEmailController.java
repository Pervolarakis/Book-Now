package com.example.Book.now.controller;

import com.example.Book.now.exceptions.EmailAlreadyVerifiedException;
import com.example.Book.now.exceptions.UserNotFoundException;
import com.example.Book.now.service.VerifyMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/verify")
@RequiredArgsConstructor
public class VerifyEmailController {

    private final VerifyMailService verifyMailService;

    @PostMapping
    public ResponseEntity<String> verifyEmail(@RequestParam String token) throws UserNotFoundException, EmailAlreadyVerifiedException {
        return ResponseEntity.ok(verifyMailService.verifyEmail(token));
    }

}
