package com.example.Book.now.repository;

import com.example.Book.now.Entities.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreLocationRepository extends JpaRepository<StoreLocation, Integer> {
    Optional<StoreLocation> findById(Integer storeId);

    List<StoreLocation> findAll();
}
