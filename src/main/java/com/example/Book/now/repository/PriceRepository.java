package com.example.Book.now.repository;

import com.example.Book.now.Entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
    Optional<Price> findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(Integer vehicleId, Integer storeId, Date fromDate, Date toDate);
}
