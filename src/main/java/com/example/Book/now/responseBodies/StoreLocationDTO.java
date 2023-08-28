package com.example.Book.now.responseBodies;

import jakarta.persistence.Id;

public record StoreLocationDTO(
        Integer storeId,
        String storeName,
        String country,
        String state,
        String city,
        String fullAddress
) {
}
