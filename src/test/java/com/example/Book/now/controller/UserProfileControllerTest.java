package com.example.Book.now.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

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
}
