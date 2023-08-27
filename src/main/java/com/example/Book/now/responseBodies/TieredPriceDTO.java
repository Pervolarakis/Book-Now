package com.example.Book.now.responseBodies;

public record TieredPriceDTO(
        Integer tieredPriceId,
        Integer durationInDays,
        Float discountPercentage
) {
}
