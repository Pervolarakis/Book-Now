package com.example.Book.now.service;

import com.example.Book.now.Entities.Coupon;
import com.example.Book.now.RequestBodies.CreateCouponRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.CouponRepository;
import com.example.Book.now.responseBodies.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;

    public List<CouponDTO> getAllCoupons(){
        return couponRepository.findAll().stream().map(
                coupon -> new CouponDTO(
                        coupon.getCouponId(),
                        coupon.getDiscountPercentage(),
                        coupon.getExpiresAt(),
                        coupon.getExpired()
                )
        ).collect(Collectors.toList());
    }

    public String createCoupon(CreateCouponRequestBody createCouponRequestBody){
        Coupon coupon = new Coupon();
        coupon.setCouponId(createCouponRequestBody.getCouponId());
        coupon.setExpiresAt(createCouponRequestBody.getExpiresAt());
        coupon.setDiscountPercentage(createCouponRequestBody.getDiscountPercentage());
        coupon.setExpired(Boolean.FALSE);
        Coupon savedCoupon = couponRepository.save(coupon);
        return savedCoupon.getCouponId();
    }

    public String deleteCoupon(String couponId) throws ResourceNotFoundException {
        Coupon coupon = couponRepository.findById(couponId)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon"));
        coupon.setExpired(Boolean.TRUE);
        Coupon savedCoupon = couponRepository.save(coupon);
        return savedCoupon.getCouponId();
    }

}
