package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateTieredPriceRequestBody;
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
public class TieredPriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void getAllTieredPriceTest() throws Exception {
        mvc.perform(get("/api/v1/tiered_price"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void getAllTieredPriceAsUserTest() throws Exception {
        mvc.perform(get("/api/v1/tiered_price"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void getAllTieredPriceAsAdminTest() throws Exception {
        mvc.perform(get("/api/v1/tiered_price"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void createTieredPriceUnauthorizedTest() throws Exception {
        CreateTieredPriceRequestBody requestBody = new CreateTieredPriceRequestBody();
        requestBody.setDiscountPercentage(10f);
        requestBody.setDurationInDays(5);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/api/v1/tiered_price")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void createTieredPriceAsUserTest() throws Exception {
        CreateTieredPriceRequestBody requestBody = new CreateTieredPriceRequestBody();
        requestBody.setDiscountPercentage(10f);
        requestBody.setDurationInDays(5);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/api/v1/tiered_price")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void createTieredPriceAsAdminTest() throws Exception {
        CreateTieredPriceRequestBody requestBody = new CreateTieredPriceRequestBody();
        requestBody.setDiscountPercentage(null);
        requestBody.setDurationInDays(5);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/api/v1/tiered_price")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setDiscountPercentage(10f);
        requestBody.setDurationInDays(null);
        mvc.perform(post("/api/v1/tiered_price")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        requestBody.setDurationInDays(5);
        mvc.perform(post("/api/v1/tiered_price")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(requestBody)))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    @Transactional
    public void disableTieredPriceUnauthenticatedTest() throws Exception {
        mvc.perform(delete("/api/v1/tiered_price/1"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void disableTieredPriceAsUserTest() throws Exception {
        mvc.perform(delete("/api/v1/tiered_price/1"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void disableTieredPriceAsAdminTest() throws Exception {
        mvc.perform(delete("/api/v1/tiered_price/55"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        mvc.perform(delete("/api/v1/tiered_price/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }
}
