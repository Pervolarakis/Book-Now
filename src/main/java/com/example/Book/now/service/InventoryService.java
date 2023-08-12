package com.example.Book.now.service;

import com.example.Book.now.repository.InventoryRepository;
import com.example.Book.now.responseBodies.InventoryDTO;
import com.example.Book.now.responseBodies.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryDTO> getAllInventory(){
        return inventoryRepository.findAll()
            .stream().map(inventory -> new InventoryDTO(
                new VehicleDTO(
                    inventory.getVehicleId().getVehicleId(),
                    inventory.getVehicleId().getName(),
                    inventory.getVehicleId().getBrand(),
                    inventory.getVehicleId().getVehicleYear(),
                    inventory.getVehicleId().getVehicleType(),
                    inventory.getVehicleId().getNumOfSeats(),
                    inventory.getVehicleId().getMileage(),
                    inventory.getVehicleId().getTransmission(),
                    inventory.getVehicleId().getFuel(),
                    inventory.getVehicleId().getNumOfBags(),
                    inventory.getVehicleId().getNumOfDoors(),
                    inventory.getVehicleId().getAc(),
                    inventory.getVehicleId().getPhoto()
                ),
                inventory.getQuantity(),
                inventory.getStoreId().getStoreId()
            )).collect(Collectors.toList());
    }

    public List<InventoryDTO> getInventoryByLocationId(Integer locationId){
        return inventoryRepository.findByStoreIdStoreId(locationId)
            .stream().map(inventory -> new InventoryDTO(
                new VehicleDTO(
                    inventory.getVehicleId().getVehicleId(),
                    inventory.getVehicleId().getName(),
                    inventory.getVehicleId().getBrand(),
                    inventory.getVehicleId().getVehicleYear(),
                    inventory.getVehicleId().getVehicleType(),
                    inventory.getVehicleId().getNumOfSeats(),
                    inventory.getVehicleId().getMileage(),
                    inventory.getVehicleId().getTransmission(),
                    inventory.getVehicleId().getFuel(),
                    inventory.getVehicleId().getNumOfBags(),
                    inventory.getVehicleId().getNumOfDoors(),
                    inventory.getVehicleId().getAc(),
                    inventory.getVehicleId().getPhoto()
                ),
                inventory.getQuantity(),
                inventory.getStoreId().getStoreId()
            )).collect(Collectors.toList());
    }

}
