package com.example.Book.now.controller;

import com.example.Book.now.Entities.UserProfile;
import com.example.Book.now.RequestBodies.UpdateUserProfileRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.UserProfileDTO;
import com.example.Book.now.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable Integer profileId) throws ResourceNotFoundException {
        return ResponseEntity.ok(userProfileService.getUserProfileById(profileId));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<Integer> updateUserProfile(@PathVariable Integer profileId, UpdateUserProfileRequestBody updateUserProfileRequestBody) throws ResourceNotFoundException {
        return ResponseEntity.ok(userProfileService.updateUserProfileById(profileId, updateUserProfileRequestBody));
    }

}
