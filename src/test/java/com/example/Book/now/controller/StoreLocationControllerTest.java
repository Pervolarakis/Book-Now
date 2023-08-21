package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateStoreLocationRequestBody;
import com.example.Book.now.RequestBodies.UpdateStoreLocationRequestBody;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreLocationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void getStoreLocationByIdTest() throws Exception {
        mvc.perform(get("/api/v1/location/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
        mvc.perform(get("/api/v1/location/600"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Transactional
    public void getAllStoreLocationsTest() throws Exception {
        mvc.perform(get("/api/v1/location"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void createStoreLocationTestUnauthorized() throws Exception {
        mvc.perform(post("/api/v1/location"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void createStoreLocationTestAsUser() throws Exception {
        mvc.perform(post("/api/v1/location"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void createStoreLocationTestAsAdmin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateStoreLocationRequestBody requestBody = new CreateStoreLocationRequestBody();
        requestBody.setStoreName("test-store-location");
        requestBody.setCity("test-city");
        requestBody.setCountry("test-country");
        requestBody.setState("test-state");
        requestBody.setFullAddress("test full address");
        mvc.perform(post("/api/v1/location")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    @Transactional
    public void updateStoreLocationTestUnauthorized() throws Exception {
        mvc.perform(put("/api/v1/location/1"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void updateStoreLocationTestAsUser() throws Exception {
        mvc.perform(put("/api/v1/location/1"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void updateStoreLocationTestAsAdmin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateStoreLocationRequestBody requestBody = new UpdateStoreLocationRequestBody();
        requestBody.setStoreName("test-store-location");
        requestBody.setCity("test-city");
        requestBody.setCountry("test-country");
        requestBody.setState("test-state");
        requestBody.setFullAddress("test full address");
        mvc.perform(put("/api/v1/location/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.OK.value()));
        mvc.perform(put("/api/v1/location/100")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }
}
