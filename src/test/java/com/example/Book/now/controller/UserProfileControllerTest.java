package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.UpdateUserProfileRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void getUserProfileByIdTest() throws Exception {
        mvc.perform(get("/api/v1/profile/55"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        mvc.perform(get("/api/v1/profile/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void updateUserByIdTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateUserProfileRequestBody updateUserProfileRequestBody = new UpdateUserProfileRequestBody();
        updateUserProfileRequestBody.setFirstName("test-firstname");
        updateUserProfileRequestBody.setLastName("test-lastname");
        updateUserProfileRequestBody.setCity("test-city");
        updateUserProfileRequestBody.setState("test-state");
        updateUserProfileRequestBody.setCountry("test-country");
        updateUserProfileRequestBody.setPhone("test-phone");
        updateUserProfileRequestBody.setDateOfBirth(new Date("02/12/1999"));
        mvc.perform(get("/api/v1/profile/55")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateUserProfileRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        mvc.perform(get("/api/v1/profile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateUserProfileRequestBody)))
                .andExpect(status().is(HttpStatus.OK.value()));
    }
}
