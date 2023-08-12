package com.example.Book.now.service;

import com.example.Book.now.responseBodies.InventoryDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;

    @Test
    @Transactional
    public void getAllInventoryTest(){
        List<InventoryDTO> inventory = inventoryService.getAllInventory();
        Assertions.assertEquals(inventory.size(), 6, "Successfully returns all inventory");
    }

    @Test
    @Transactional
    public void getInventoryByStoreIdTest(){
        List<InventoryDTO> inventory = inventoryService.getInventoryByLocationId(55);
        Assertions.assertEquals(inventory.size(), 0, "Successfully returns all inventory");
        List<InventoryDTO> inventory2 = inventoryService.getInventoryByLocationId(1);
        Assertions.assertEquals(inventory2.size(), 3, "Successfully returns all inventory");
    }
}
