package com.example.Book.now.responseBodies;

import com.example.Book.now.Entities.Vehicle;

public record InventoryDTO(
        VehicleDTO vehicle,
        Integer quantity,
        Integer storeId
) {
}
