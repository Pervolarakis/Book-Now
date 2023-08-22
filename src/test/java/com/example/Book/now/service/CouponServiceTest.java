package com.example.Book.now.service;

import com.example.Book.now.Entities.Coupon;
import com.example.Book.now.RequestBodies.CreateCouponRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.CouponRepository;
import com.example.Book.now.responseBodies.CouponDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CouponServiceTest {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponRepository couponRepository;

    @Test
    @Transactional
    public void getAllCouponsTest(){
        Assertions.assertDoesNotThrow(() -> couponService.getAllCoupons(), "Successfully returns all coupons");
        List<CouponDTO> allCoupons = couponService.getAllCoupons();
        Assertions.assertEquals(allCoupons.size(), 2, "Successfully returns all coupons");
    }

    @Test
    @Transactional
    public void createCouponTest(){
        CreateCouponRequestBody requestBody = new CreateCouponRequestBody();
        requestBody.setCouponId("test-coupon");
        requestBody.setExpiresAt(new Date("12/31/2023"));
        requestBody.setDiscountPercentage(10f);
        Assertions.assertDoesNotThrow(() -> couponService.createCoupon(requestBody), "Doesn't throw when creating coupon");
        String couponId = couponService.createCoupon(requestBody);
        Assertions.assertNotNull(couponRepository.findById(couponId), "Coupon exists");
    }

    @Test
    @Transactional
    public void deleteCouponTest() throws ResourceNotFoundException {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> couponService.deleteCoupon("aaaa"), "Throws if coupon doesn't exist");
        String couponId = couponService.deleteCoupon("7A75G5Y1");
        Assertions.assertEquals(couponRepository.findById("7A75G5Y1").get().getExpired(), Boolean.TRUE, "successfully disables coupon");
    }
}
