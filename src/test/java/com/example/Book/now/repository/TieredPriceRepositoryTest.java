package com.example.Book.now.repository;

import com.example.Book.now.Entities.TieredPrice;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TieredPriceRepositoryTest {

    @Autowired
    private TieredPriceRepository tieredPricingRepository;

    @Test
    @Transactional
    public void findDurationInDaysLessThanEqualTest(){
        Assertions.assertDoesNotThrow(() -> tieredPricingRepository.findTopByDurationInDaysLessThanEqualOrderByDurationInDaysDesc(35), "Successfully finds tiered price");
        TieredPrice tieredPrice = tieredPricingRepository.findTopByDurationInDaysLessThanEqualOrderByDurationInDaysDesc(35).get();
        Assertions.assertEquals(tieredPrice.getDiscountPercentage(), 13, "Successfully returns correct price");
    }

}
