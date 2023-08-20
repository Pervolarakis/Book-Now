package com.example.Book.now.service;

import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.StoreLocationRepository;
import com.example.Book.now.responseBodies.StoreLocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreLocationService {

    private final StoreLocationRepository storeLocationRepository;

    public StoreLocationDTO getStoreLocationById(Integer storeLocationId) throws ResourceNotFoundException {
        return storeLocationRepository.findById(storeLocationId).map(
            storeLocation -> new StoreLocationDTO(
                storeLocation.getStoreId(),
                storeLocation.getStoreName(),
                storeLocation.getCountry(),
                storeLocation.getState(),
                storeLocation.getCity(),
                storeLocation.getFullAddress()
            )
        ).orElseThrow(() -> new ResourceNotFoundException("Store Location"));
    }

    public List<StoreLocationDTO> getAllStoreLocations(){
        return storeLocationRepository.findAll().stream().map(
            storeLocation -> new StoreLocationDTO(
                storeLocation.getStoreId(),
                storeLocation.getStoreName(),
                storeLocation.getCountry(),
                storeLocation.getState(),
                storeLocation.getCity(),
                storeLocation.getFullAddress()
            )
        ).collect(Collectors.toList());
    }

//    public Integer

}
