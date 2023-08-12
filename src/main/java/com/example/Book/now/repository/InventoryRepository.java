package com.example.Book.now.repository;

import com.example.Book.now.Entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findAll();
    List<Inventory> findByStoreIdStoreId(Integer storeId);
}
