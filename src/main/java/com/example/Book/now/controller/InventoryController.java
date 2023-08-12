package com.example.Book.now.controller;

import com.example.Book.now.Entities.Inventory;
import com.example.Book.now.responseBodies.InventoryDTO;
import com.example.Book.now.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getAllInventory(){
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{storeId}")
    public List<InventoryDTO> getInventoryByStoreId(@PathVariable Integer storeId){
        return inventoryService.getInventoryByLocationId(storeId);
    }

}
