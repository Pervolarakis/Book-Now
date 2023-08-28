package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateReviewRequestBody;
import com.example.Book.now.RequestBodies.UpdateReviewRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void getReviewsByVehicleIdTest() throws Exception {
        mvc.perform(get("/api/v1/review/vehicle/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void getReviewsByUserIdTest() throws Exception {
        mvc.perform(get("/api/v1/review/user/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void createReviewUnauthorizedTest() throws Exception {
        CreateReviewRequestBody requestBody = new CreateReviewRequestBody();
        ObjectMapper mapper = new ObjectMapper();
        requestBody.setReviewText("very nice!");
        requestBody.setRating(3);
        requestBody.setBookingId(1);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void createReviewTest() throws Exception {
        CreateReviewRequestBody requestBody = new CreateReviewRequestBody();
        ObjectMapper mapper = new ObjectMapper();
        requestBody.setReviewText("test-aa");
        requestBody.setRating(-1);
        requestBody.setBookingId(1);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setRating(6);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setRating(3);
        requestBody.setReviewText(null);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setReviewText("very nice!");
        requestBody.setRating(null);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setRating(3);
        requestBody.setBookingId(null);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setBookingId(1);
        requestBody.setBookingId(100);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setBookingId(2);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
        requestBody.setBookingId(1);
        mvc.perform(post("/api/v1/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    @Transactional
    public void updateReviewUnauthorizedTest() throws Exception {
        UpdateReviewRequestBody requestBody = new UpdateReviewRequestBody();
        ObjectMapper mapper = new ObjectMapper();
        requestBody.setReviewText("very nice!");
        requestBody.setRating(3);
        mvc.perform(put("/api/v1/review/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void updateReviewTest() throws Exception {
        UpdateReviewRequestBody requestBody = new UpdateReviewRequestBody();
        ObjectMapper mapper = new ObjectMapper();
        requestBody.setReviewText("very nice!");
        requestBody.setRating(50);
        mvc.perform(put("/api/v1/review/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setRating(-1);
        mvc.perform(put("/api/v1/review/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setRating(3);
        mvc.perform(put("/api/v1/review/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
        mvc.perform(put("/api/v1/review/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.OK.value()));
        mvc.perform(put("/api/v1/review/100")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Transactional
    public void deleteReviewUnauthorizedTest() throws Exception {
        mvc.perform(delete("/api/v1/review/1"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void deleteReviewTest() throws Exception {
        mvc.perform(delete("/api/v1/review/200"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        mvc.perform(delete("/api/v1/review/2"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
        mvc.perform(delete("/api/v1/review/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }
}
