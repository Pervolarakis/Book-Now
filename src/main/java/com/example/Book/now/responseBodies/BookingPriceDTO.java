package com.example.Book.now.responseBodies;

public record BookingPriceDTO(
        Float totalPriceBeforeDiscount,
        Float totalPriceAfterDiscount,
        Float tieredPriceDiscount,
        Float couponDiscount
) {
}
