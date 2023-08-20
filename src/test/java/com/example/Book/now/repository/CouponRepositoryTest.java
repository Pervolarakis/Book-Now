package com.example.Book.now.repository;

import com.example.Book.now.Entities.Coupon;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CouponRepositoryTest {

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @Transactional
    public void findCouponTest(){
        Assertions.assertDoesNotThrow(() -> couponRepository.findById("7A75G5Y1"), "Successfully finds coupon");
        Coupon coupon = couponRepository.findById("7A75G5Y1").get();
        Assertions.assertEquals(coupon.getDiscountPercentage(), 10, "Successfully returns correct discount");
    }

}
