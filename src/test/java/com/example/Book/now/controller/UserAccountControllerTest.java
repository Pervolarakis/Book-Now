package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.example.Book.now.RequestBodies.RegisterRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @RegisterExtension
    private static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot", "secret"))
            .withPerMethodLifecycle(true);

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

    @Test
    @Transactional
    public void userAccountRegisterTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RegisterRequestBody requestBody = new RegisterRequestBody();
        requestBody.setEmail(null);
        requestBody.setPassword("testpass123");
        requestBody.setCity("test-city");
        requestBody.setPhone("test-phone");
        requestBody.setState("test-state");
        requestBody.setCountry("test-country");
        requestBody.setFirstName("test-firstname");
        requestBody.setLastName("test-lastname");
        requestBody.setDateOfBirth(new Date("2022/08/13"));
        //Throws 400 if email is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setEmail("test-mail");
        //Throws 400 if email is invalid
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setEmail("test-mail@gmail.com");
        requestBody.setPassword(null);
        //Throws 400 if password is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setPassword("tes");
        //Throws 400 if password is shorter than 8 chars
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setPassword("test-pass");
        requestBody.setCity(null);
        //Throws 400 if city is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setCity("test-city");
        requestBody.setPhone(null);
        //Throws 400 if phone is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setPhone("test-phone");
        requestBody.setState(null);
        //Throws 400 if state is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setState("test-state");
        requestBody.setCountry(null);
        //Throws 400 if country is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setCountry("test-country");
        requestBody.setFirstName(null);
        //Throws 400 if first name is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setFirstName("test-firstname");
        requestBody.setLastName(null);
        //Throws 400 if last name is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setLastName("test-lastname");
        requestBody.setDateOfBirth(null);
        //Throws 400 if date is empty
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setDateOfBirth(new Date("2022/08/13"));
        //returns 200 if everything is ok
        mvc.perform(post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

}
