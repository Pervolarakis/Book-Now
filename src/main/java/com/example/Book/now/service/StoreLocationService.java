package com.example.Book.now.service;

import com.example.Book.now.Entities.StoreLocation;
import com.example.Book.now.RequestBodies.CreateStoreLocationRequestBody;
import com.example.Book.now.RequestBodies.UpdateStoreLocationRequestBody;
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

    public Integer createStoreLocation (CreateStoreLocationRequestBody createStoreLocationRequestBody){
        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setStoreName(createStoreLocationRequestBody.getStoreName());
        storeLocation.setCity(createStoreLocationRequestBody.getCity());
        storeLocation.setCountry(createStoreLocationRequestBody.getCountry());
        storeLocation.setState(createStoreLocationRequestBody.getState());
        storeLocation.setFullAddress(createStoreLocationRequestBody.getFullAddress());
        StoreLocation savedStoreLocation = storeLocationRepository.save(storeLocation);
        return savedStoreLocation.getStoreId();
    }

    public Integer updateStoreLocation(UpdateStoreLocationRequestBody updateStoreLocationRequestBody, Integer storeLocationId) throws ResourceNotFoundException {
        StoreLocation storeLocation = storeLocationRepository.findById(storeLocationId)
            .orElseThrow(() -> new ResourceNotFoundException("Store Location"));
        storeLocation.setFullAddress(updateStoreLocationRequestBody.getFullAddress());
        storeLocation.setStoreName(updateStoreLocationRequestBody.getStoreName());
        storeLocation.setCity(updateStoreLocationRequestBody.getCity());
        storeLocation.setCountry(updateStoreLocationRequestBody.getCountry());
        storeLocation.setState(updateStoreLocationRequestBody.getState());
        StoreLocation savedStoreLocation = storeLocationRepository.save(storeLocation);
        return savedStoreLocation.getStoreId();
    }

}
