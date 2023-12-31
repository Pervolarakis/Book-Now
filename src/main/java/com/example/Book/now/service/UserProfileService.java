package com.example.Book.now.service;

import com.example.Book.now.Entities.UserProfile;
import com.example.Book.now.RequestBodies.UpdateUserProfileRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.UserProfileRepository;
import com.example.Book.now.responseBodies.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileDTO getUserProfileById(Integer profileId) throws ResourceNotFoundException {
        return userProfileRepository.findByUserIdUserId(profileId)
                .map(userProfile ->  new UserProfileDTO(
                        userProfile.getUserId().getUserId(),
                        userProfile.getFirstName(),
                        userProfile.getLastName(),
                        userProfile.getPhone(),
                        userProfile.getCountry(),
                        userProfile.getState(),
                        userProfile.getCity(),
                        userProfile.getDateOfBirth()
                ))
            .orElseThrow(() -> new ResourceNotFoundException("User"));
    }

    public Integer updateUserProfileById(Integer profileId, UpdateUserProfileRequestBody updateUserProfileRequestBody) throws ResourceNotFoundException {
        UserProfile userProfile = userProfileRepository.findByUserIdUserId(profileId)
            .orElseThrow(() -> new ResourceNotFoundException("User"));
        userProfile.setFirstName(updateUserProfileRequestBody.getFirstName());
        userProfile.setLastName(updateUserProfileRequestBody.getLastName());
        userProfile.setCity(updateUserProfileRequestBody.getCity());
        userProfile.setState(updateUserProfileRequestBody.getState());
        userProfile.setCountry(updateUserProfileRequestBody.getCountry());
        userProfile.setPhone(updateUserProfileRequestBody.getPhone());
        userProfile.setDateOfBirth(updateUserProfileRequestBody.getDateOfBirth());
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        return savedUserProfile.getId();
    }
}
