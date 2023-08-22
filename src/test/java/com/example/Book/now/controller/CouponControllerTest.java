package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateCouponRequestBody;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void getAllCouponsUnauthenticatedTest() throws Exception {
        mvc.perform(get("/api/v1/coupon"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("pemanuele1@census.gov")
    public void getAllCouponsAsUserTest() throws Exception {
        mvc.perform(get("/api/v1/coupon"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void getAllCouponsAsAdminTest() throws Exception {
        mvc.perform(get("/api/v1/coupon"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void createCouponUnauthenticatedTest() throws Exception {
        mvc.perform(post("/api/v1/coupon"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("pemanuele1@census.gov")
    public void createCouponAsUserTest() throws Exception {
        mvc.perform(post("/api/v1/coupon"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void createCouponAsAdminTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateCouponRequestBody createCouponRequestBody = new CreateCouponRequestBody();
        createCouponRequestBody.setCouponId(null);
        createCouponRequestBody.setExpiresAt(new Date("12/31/2023"));
        createCouponRequestBody.setDiscountPercentage(10f);
        mvc.perform(post("/api/v1/coupon")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createCouponRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createCouponRequestBody.setCouponId("test-coupon");
        createCouponRequestBody.setExpiresAt(null);
        mvc.perform(post("/api/v1/coupon")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createCouponRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createCouponRequestBody.setExpiresAt(new Date("12/31/2023"));
        createCouponRequestBody.setDiscountPercentage(null);
        mvc.perform(post("/api/v1/coupon")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createCouponRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createCouponRequestBody.setDiscountPercentage(10f);
        mvc.perform(post("/api/v1/coupon")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createCouponRequestBody)))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    @Transactional
    public void deleteCouponUnauthenticatedTest() throws Exception {
        mvc.perform(delete("/api/v1/coupon/abv456g"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("pemanuele1@census.gov")
    public void deleteCouponAsUserTest() throws Exception {
        mvc.perform(delete("/api/v1/coupon/abv456g"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void deleteInvalidCouponAsAdminTest() throws Exception {
        mvc.perform(delete("/api/v1/coupon/abv456g"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void deleteCouponAsAdminTest() throws Exception {
        mvc.perform(delete("/api/v1/coupon/7A75G5Y1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }
}
