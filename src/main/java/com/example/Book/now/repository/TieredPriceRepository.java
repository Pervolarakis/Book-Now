package com.example.Book.now.repository;

import com.example.Book.now.Entities.TieredPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TieredPriceRepository extends JpaRepository<TieredPrice, Integer> {

    Optional<TieredPrice> findByDurationInDaysLessThanEqual(Integer durationInDays);
    Optional<TieredPrice> findTopByOrderByDurationInDaysDesc();

    Optional<TieredPrice> findTopByDurationInDaysLessThanEqualOrderByDurationInDaysDesc(Integer durationInDays);
}
