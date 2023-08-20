package com.example.Book.now.repository;

import com.example.Book.now.Entities.Price;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    @Transactional
    public void findVehiclePriceTest(){
        Assertions.assertDoesNotThrow(() -> priceRepository.findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(1,1, LocalDate.parse("2022-08-25"),  LocalDate.parse("2022-08-25")));
        Float price = priceRepository.findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(1,1, LocalDate.parse("2022-08-25"),  LocalDate.parse("2022-08-25")).get().getPrice();
        Assertions.assertEquals(price.floatValue(), 125.88f, "Successfully returns price");
    }
}
