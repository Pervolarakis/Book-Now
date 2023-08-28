package com.example.Book.now.service;

import com.example.Book.now.Entities.TieredPrice;
import com.example.Book.now.RequestBodies.CreateTieredPriceRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.TieredPriceRepository;
import com.example.Book.now.responseBodies.TieredPriceDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TieredPriceServiceTest {

    @Autowired
    private TieredPriceService tieredPriceService;
    @Autowired
    private TieredPriceRepository tieredPriceRepository;

    @Test
    @Transactional
    public void getAllTieredPriceTest(){
        List<TieredPriceDTO> allTieredPrice = tieredPriceService.getAllTieredPrice();
        Assertions.assertEquals(allTieredPrice.size(), 3, "Successfully returns all tiered price");
    }

    @Test
    @Transactional
    public void createTieredPriceTest(){
        CreateTieredPriceRequestBody requestBody = new CreateTieredPriceRequestBody();
        requestBody.setDiscountPercentage(10f);
        requestBody.setDurationInDays(5);
        Integer tieredPrice = tieredPriceService.createTieredPrice(requestBody);
        Assertions.assertNotNull(tieredPriceRepository.findById(tieredPrice), "Tiered price exists");
    }

    @Test
    @Transactional
    public void deleteTieredPriceTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> tieredPriceService.disableTieredPrice(50), "Throws if tiered price doesnt exist");
        Assertions.assertDoesNotThrow(() -> tieredPriceService.disableTieredPrice(1), "Doesnt throw if tiered price exists");
        TieredPrice tieredPrice = tieredPriceRepository.findById(1).get();
        Assertions.assertEquals(tieredPrice.getIsActive(), Boolean.FALSE, "Tiered price is successfully inactivated");
    }
}
