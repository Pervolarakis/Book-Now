package com.example.Book.now.service;

import com.example.Book.now.Entities.StoreLocation;
import com.example.Book.now.RequestBodies.CreateStoreLocationRequestBody;
import com.example.Book.now.RequestBodies.UpdateStoreLocationRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.responseBodies.StoreLocationDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StoreLocationServiceTest {

    @Autowired
    private StoreLocationService storeLocationService;

    @Test
    @Transactional
    public void getStoreLocationByIdTest() throws ResourceNotFoundException {
        Assertions.assertNotNull(storeLocationService.getStoreLocationById(1), "Successfully returns store location");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> storeLocationService.getStoreLocationById(4), "Throws if location doesn't exist");
    }

    @Test
    @Transactional
    public void gerAllStoreLocationsTest(){
        Assertions.assertDoesNotThrow(() -> storeLocationService.getAllStoreLocations(), "Successfully returns all stores");
        List<StoreLocationDTO> allStoreLocations = storeLocationService.getAllStoreLocations();
        Assertions.assertEquals(allStoreLocations.size(), 2, "Returns right amount of stores");
    }

    @Test
    @Transactional
    public void createStoreLocationTest() throws ResourceNotFoundException {
        CreateStoreLocationRequestBody createStoreLocationRequestBody = new CreateStoreLocationRequestBody();
        createStoreLocationRequestBody.setStoreName("test-store-location");
        createStoreLocationRequestBody.setCity("test-city");
        createStoreLocationRequestBody.setCountry("test-country");
        createStoreLocationRequestBody.setState("test-state");
        createStoreLocationRequestBody.setFullAddress("test full address");
        Assertions.assertDoesNotThrow(() -> storeLocationService.createStoreLocation(createStoreLocationRequestBody));
        Integer newStoreLocationId = storeLocationService.createStoreLocation(createStoreLocationRequestBody);
        Assertions.assertNotNull(storeLocationService.getStoreLocationById(newStoreLocationId));
    }

    @Test
    @Transactional
    public void updateStoreLocationTest() throws ResourceNotFoundException {
        UpdateStoreLocationRequestBody updateStoreLocationRequestBody = new UpdateStoreLocationRequestBody();
        updateStoreLocationRequestBody.setStoreName("test-store-location");
        updateStoreLocationRequestBody.setCity("test-city");
        updateStoreLocationRequestBody.setCountry("test-country");
        updateStoreLocationRequestBody.setState("test-state");
        updateStoreLocationRequestBody.setFullAddress("test full address");
        Assertions.assertDoesNotThrow(() -> storeLocationService.updateStoreLocation(updateStoreLocationRequestBody, 1), "Doesn't throw if store exists");
        Assertions.assertEquals(storeLocationService.getStoreLocationById(1).storeName(), "test-store-location", "store is successfully updated");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> storeLocationService.updateStoreLocation(updateStoreLocationRequestBody, 50), "Throws if store doesnt exist");
    }

}
