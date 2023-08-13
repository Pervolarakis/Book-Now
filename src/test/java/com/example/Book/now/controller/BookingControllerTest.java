package com.example.Book.now.controller;

import com.example.Book.now.RequestBodies.CreateBookingRequestBody;
import com.example.Book.now.RequestBodies.UpdateBookingRequestBody;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    @WithUserDetails("pemanuele1@census.gov")
    public void getBookingByIdAsRandomUserTest() throws Exception {
        mvc.perform(get("/api/v1/booking/1"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void getBookingByIdAsAdminTest() throws Exception {
        mvc.perform(get("/api/v1/booking/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void getBookingByIdAsOwnerTest() throws Exception {
        mvc.perform(get("/api/v1/booking/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void getBookingByIdAsNotAuthenticatedTest() throws Exception {
        mvc.perform(get("/api/v1/booking/1"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void getBookingWithInvalidIdTest() throws Exception {
        mvc.perform(get("/api/v1/booking/55"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Transactional
    public void getBookingByLocationIdUnauthorizedTest() throws Exception {
        mvc.perform(get("/api/v1/booking/location/1"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void getBookingByLocationIdAsUserTest() throws Exception {
        mvc.perform(get("/api/v1/booking/location/1"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void getBookingByLocationIdAsAdminTest() throws Exception {
        mvc.perform(get("/api/v1/booking/location/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void getBookingByUserIdTest() throws Exception {
        mvc.perform(get("/api/v1/booking/user/1"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("pemanuele1@census.gov")
    public void getBookingByUserIdAsNotOwnerTest() throws Exception {
        mvc.perform(get("/api/v1/booking/user/1"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void getBookingByUserIdAsOwnerTest() throws Exception {
        mvc.perform(get("/api/v1/booking/user/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void getBookingByUserIdAsAdminTest() throws Exception {
        mvc.perform(get("/api/v1/booking/user/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void getAllBookingsAsUnauthorizedTest() throws Exception {
        mvc.perform(get("/api/v1/booking"))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void getAllBookingsAsUserTest() throws Exception {
        mvc.perform(get("/api/v1/booking"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void getAllBookingsAsAdminTest() throws Exception {
        mvc.perform(get("/api/v1/booking"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void createBookingUnauthenticatedTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateBookingRequestBody createBookingRequestBody = new CreateBookingRequestBody();
        createBookingRequestBody.setPickupDate(new Date("02/11/2023"));
        createBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        createBookingRequestBody.setQuantity(1);
        createBookingRequestBody.setVehicleId(1);
        createBookingRequestBody.setPickupLocationId(2);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }
    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void createBookingTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateBookingRequestBody createBookingRequestBody = new CreateBookingRequestBody();
        createBookingRequestBody.setPickupDate(null);
        createBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        createBookingRequestBody.setQuantity(1);
        createBookingRequestBody.setVehicleId(1);
        createBookingRequestBody.setPickupLocationId(2);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setPickupDate(new Date("02/11/2023"));
        createBookingRequestBody.setDeliveryDate(null);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        createBookingRequestBody.setQuantity(null);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setQuantity(0);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setQuantity(1);
        createBookingRequestBody.setVehicleId(null);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setVehicleId(1);
        createBookingRequestBody.setPickupLocationId(null);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setPickupLocationId(2);
        //create with invalid vehicle id
        createBookingRequestBody.setVehicleId(55);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setVehicleId(1);
        //crate with invalid location id
        createBookingRequestBody.setPickupLocationId(55);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        createBookingRequestBody.setPickupLocationId(2);
        mvc.perform(post("/api/v1/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createBookingRequestBody)))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    @Transactional
    public void updateBookingUnauthenticatedTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBookingRequestBody updateBookingRequestBody = new UpdateBookingRequestBody();
        updateBookingRequestBody.setPickupDate(new Date("02/11/2023"));
        updateBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        updateBookingRequestBody.setQuantity(1);
        updateBookingRequestBody.setVehicleId(1);
        updateBookingRequestBody.setPickupLocationId(2);
        mvc.perform(put("/api/v1/booking/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateBookingRequestBody)))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("pemanuele1@census.gov")
    public void updateBookingAsNotOwnerTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBookingRequestBody updateBookingRequestBody = new UpdateBookingRequestBody();
        updateBookingRequestBody.setPickupDate(new Date("02/11/2023"));
        updateBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        updateBookingRequestBody.setQuantity(1);
        updateBookingRequestBody.setVehicleId(1);
        updateBookingRequestBody.setPickupLocationId(2);
        mvc.perform(put("/api/v1/booking/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateBookingRequestBody)))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("kpink0@telegraph.co.uk")
    public void updateBookingAsOwnerTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBookingRequestBody updateBookingRequestBody = new UpdateBookingRequestBody();
        updateBookingRequestBody.setPickupDate(new Date("02/11/2023"));
        updateBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        updateBookingRequestBody.setQuantity(1);
        updateBookingRequestBody.setVehicleId(1);
        updateBookingRequestBody.setPickupLocationId(2);
        //create with invalid vehicle id
        updateBookingRequestBody.setVehicleId(55);
        mvc.perform(put("/api/v1/booking/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        updateBookingRequestBody.setVehicleId(1);
        //crate with invalid location id
        updateBookingRequestBody.setPickupLocationId(55);
        mvc.perform(put("/api/v1/booking/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        updateBookingRequestBody.setPickupLocationId(2);
        mvc.perform(put("/api/v1/booking/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateBookingRequestBody)))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void updateBookingWithWrongIdTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBookingRequestBody updateBookingRequestBody = new UpdateBookingRequestBody();
        updateBookingRequestBody.setPickupDate(new Date("02/11/2023"));
        updateBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        updateBookingRequestBody.setQuantity(1);
        updateBookingRequestBody.setVehicleId(1);
        updateBookingRequestBody.setPickupLocationId(2);
        mvc.perform(put("/api/v1/booking/55")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateBookingRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin@mail.com")
    public void updateBookingAsAdminTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateBookingRequestBody updateBookingRequestBody = new UpdateBookingRequestBody();
        updateBookingRequestBody.setPickupDate(new Date("02/11/2023"));
        updateBookingRequestBody.setDeliveryDate(new Date("05/11/2023"));
        updateBookingRequestBody.setQuantity(1);
        updateBookingRequestBody.setVehicleId(1);
        updateBookingRequestBody.setPickupLocationId(2);
        mvc.perform(put("/api/v1/booking/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(updateBookingRequestBody)))
            .andExpect(status().is(HttpStatus.OK.value()));
    }
}
