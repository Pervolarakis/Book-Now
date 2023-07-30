package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void userAccountLoginTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        LoginRequestBody requestBody = new LoginRequestBody();
        requestBody.setEmail("test-mail");
        requestBody.setPassword("test-pass");
        //Throws 400 if email has invalid format
        mvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setEmail(null);
        //throws 400 if mail is empty
        mvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setEmail("test-mail@gmail.com");
        requestBody.setPassword(null);
        //throws 400 if password is empty
        mvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setPassword("aaaaaaaaa");
        //throws 401 if credentials are invalid
        mvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
        //returns 400 if email is not verified
        requestBody.setEmail("kpink0@telegraph.co.uk");
        requestBody.setPassword("password");
        mvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        //returns 200 if everything is ok
        requestBody.setEmail("admin@mail.com");
        mvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

}
