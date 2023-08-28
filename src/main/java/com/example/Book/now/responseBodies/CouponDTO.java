package com.example.Book.now.responseBodies;

import java.util.Date;

public record CouponDTO(
        String couponId,
        Float discountPercentage,
        Date expiresAt,
        Boolean expired
) {
}
