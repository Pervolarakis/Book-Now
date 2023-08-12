package com.example.Book.now.service;

import com.example.Book.now.Entities.UserProfile;
import com.example.Book.now.RequestBodies.UpdateUserProfileRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.h2.command.dml.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @Test
    @Transactional
    public void getUserProfileByIdTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> userProfileService.getUserProfileById(55), "User should exist");
        Assertions.assertDoesNotThrow(()-> userProfileService.getUserProfileById(1), "Successfully returns user");
    }

    @Test
    @Transactional
    public void updateUserProfileByIdTest() throws ResourceNotFoundException {
        UpdateUserProfileRequestBody updateUserProfileRequestBody = new UpdateUserProfileRequestBody();
        updateUserProfileRequestBody.setFirstName("test-firstname");
        updateUserProfileRequestBody.setLastName("test-lastname");
        updateUserProfileRequestBody.setCity("test-city");
        updateUserProfileRequestBody.setState("test-state");
        updateUserProfileRequestBody.setCountry("test-country");
        updateUserProfileRequestBody.setPhone("test-phone");
        updateUserProfileRequestBody.setDateOfBirth(new Date("02/12/1999"));
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> userProfileService.updateUserProfileById(55, updateUserProfileRequestBody));
        Assertions.assertDoesNotThrow(() -> userProfileService.updateUserProfileById(1, updateUserProfileRequestBody));
        UserProfile updatedProfile = userProfileService.getUserProfileById(1);
        Assertions.assertEquals(updatedProfile.getFirstName(), "test-firstname", "First name should be updated");
    }

}
