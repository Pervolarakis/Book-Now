package com.example.Book.now.repository;

import com.example.Book.now.Entities.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreLocationRepository extends JpaRepository<StoreLocation, Integer> {
    Optional<StoreLocation> findById(Integer storeId);
}
