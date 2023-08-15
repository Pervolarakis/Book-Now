package com.example.Book.now.repository;

import com.example.Book.now.Entities.Price;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    @Transactional
    public void findVehiclePriceTest(){
        Assertions.assertDoesNotThrow(() -> priceRepository.findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(1,1,new Date("08/25/2022"), new Date("08/25/2022")));
        Optional<Price> price = priceRepository.findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(1,1,new Date("08/25/2022"), new Date("08/25/2022"));
        System.out.println(price.get().getPrice());
//        Price price = priceRepository.findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(1,1,new Date("25/08/2022"), new Date("25/08/2022"))
//                .orElseThrow(() -> new RuntimeException("AAA"));
//        System.out.println(price);
    }
}
