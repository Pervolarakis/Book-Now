package com.example.Book.now.service;

import com.example.Book.now.exceptions.VehicleNotAvailableException;
import com.example.Book.now.responseBodies.BookingPriceDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class BookingPriceServiceTest {

    @Autowired
    private BookingPriceService bookingPriceService;

    @Test
    @Transactional
    public void calculateBookingPriceTest() throws VehicleNotAvailableException {
        Assertions.assertDoesNotThrow(() -> bookingPriceService.calculateBookingCost(1,1, new Date("08/25/2022"), new Date("09/03/2023"), "aaaaaaaaa"), "Successfully calculates cost");

        BookingPriceDTO bookingPrice = bookingPriceService.calculateBookingCost(1,1, new Date("01/15/2023"), new Date("02/03/2023"), "aaaaaaaaa");

        Float priceBeforeDiscount = bookingPrice.totalPriceBeforeDiscount();

        Assertions.assertEquals(priceBeforeDiscount, 2447.4f, 0.001,"Successfully returns price before discount");

        Assertions.assertEquals(bookingPrice.tieredPriceDiscount(), 10, "Successfully calculates dates and returns discount");

        Assertions.assertEquals(bookingPrice.couponDiscount(), 0, "Successfully finds coupon discount percentage");

        Assertions.assertEquals(bookingPrice.totalPriceAfterDiscount(), 2202.66f, 0.001, "Successfully returns final price");

        Assertions.assertThrows(VehicleNotAvailableException.class, () -> bookingPriceService.calculateBookingCost(1,1, new Date("08/25/2022"), new Date("12/03/2023"), "aaaaaaaaa"), "Throws if there is no price available for this date range");
    }

}
