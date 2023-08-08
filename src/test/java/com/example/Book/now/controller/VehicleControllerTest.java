package com.example.Book.now.controller;

import com.example.Book.now.Entities.FuelType;
import com.example.Book.now.Entities.TransmissionType;
import com.example.Book.now.RequestBodies.CreateVehicleRequestBody;
import com.example.Book.now.RequestBodies.LoginRequestBody;
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

import static java.lang.Boolean.TRUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void getAllVehiclesTest() throws Exception {
        mvc.perform(get("/api/v1/vehicle"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Transactional
    public void getVehicleByIdTest() throws Exception {
        mvc.perform(get("/api/v1/vehicle/1"))
            .andExpect(status().is(HttpStatus.OK.value()));
        mvc.perform(get("/api/v1/vehicle/5"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Transactional
    public void createVehicleUnauthenticatedTest() throws Exception {
        mvc.perform(post("/api/v1/vehicle"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @WithUserDetails("pemanuele1@census.gov")
    @Transactional
    public void createVehicleAsUserTest() throws Exception {
        mvc.perform(post("/api/v1/vehicle"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @WithUserDetails("admin@mail.com")
    @Transactional
    public void createVehicleAsAdminTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateVehicleRequestBody vehicleRequestBody = new CreateVehicleRequestBody();
        vehicleRequestBody.setName("");
        vehicleRequestBody.setBrand("Acura");
        vehicleRequestBody.setYear(1999);
        vehicleRequestBody.setVehicleType("coupe");
        vehicleRequestBody.setNumOfSeats(5);
        vehicleRequestBody.setMileage(50000);
        vehicleRequestBody.setNumOfBags(2);
        vehicleRequestBody.setNumOfDoors(5);
        vehicleRequestBody.setAc(TRUE);
        vehicleRequestBody.setPhoto("http://dummyimage.com/119x100.png/5fa2dd/ffffff");
        vehicleRequestBody.setTransmission(TransmissionType.Automatic);
        vehicleRequestBody.setFuel(FuelType.Diesel);
        //name should be valid
        mvc.perform(post("/api/v1/vehicle")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(vehicleRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setName("test-name");
        vehicleRequestBody.setBrand("");
        //brand should be valid
        mvc.perform(post("/api/v1/vehicle")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(vehicleRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setBrand("Acura");
        vehicleRequestBody.setYear(null);
        //year should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setYear(1999);
        vehicleRequestBody.setVehicleType("");
        //vehicle type should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setVehicleType("coupe");
        vehicleRequestBody.setNumOfSeats(null);
        //num of seats should be valid
        mvc.perform(post("/api/v1/vehicle")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(vehicleRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setNumOfSeats(5);
        vehicleRequestBody.setMileage(null);
        //mileage should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setMileage(50000);
        vehicleRequestBody.setTransmission(null);
        //transmission should be valid
        mvc.perform(post("/api/v1/vehicle")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(vehicleRequestBody)))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setTransmission(TransmissionType.Automatic);
        vehicleRequestBody.setFuel(null);
        //fuel type should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setFuel(FuelType.Diesel);
        vehicleRequestBody.setNumOfBags(null);
        //num of bags should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setNumOfBags(2);
        vehicleRequestBody.setNumOfDoors(null);
        //num of doors should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setNumOfDoors(5);
        vehicleRequestBody.setAc(null);
        //ac value should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setAc(TRUE);
        vehicleRequestBody.setPhoto("");
        //photo should be valid
        mvc.perform(post("/api/v1/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleRequestBody)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        vehicleRequestBody.setPhoto("http://dummyimage.com/119x100.png/5fa2dd/ffffff");
        mvc.perform(post("/api/v1/vehicle")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(vehicleRequestBody)))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }
}
