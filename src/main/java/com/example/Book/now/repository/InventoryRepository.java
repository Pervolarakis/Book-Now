package com.example.Book.now.repository;

import com.example.Book.now.Entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findAll();
    List<Inventory> findByStoreIdStoreId(Integer storeId);
    Optional<Inventory> findByStoreIdStoreIdAndVehicleIdVehicleId(Integer storeId, Integer vehicleId);
}
