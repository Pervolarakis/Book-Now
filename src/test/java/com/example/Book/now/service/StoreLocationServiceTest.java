package com.example.Book.now.service;

import com.example.Book.now.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreLocationServiceTest {

    @Autowired
    private StoreLocationService storeLocationService;

    @Test
    @Transactional
    public void getStoreLocationByIdTest(){
        Assertions.assertDoesNotThrow(() -> storeLocationService.getStoreLocationById(1), "Successfully returns store location");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> storeLocationService.getStoreLocationById(4), "Throws if location doesn't exist");
    }

}
