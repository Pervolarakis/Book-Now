package com.example.Book.now.service;

import com.example.Book.now.Entities.TieredPrice;
import com.example.Book.now.RequestBodies.CreateTieredPriceRequestBody;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.TieredPriceRepository;
import com.example.Book.now.responseBodies.TieredPriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TieredPriceService {

    private final TieredPriceRepository tieredPriceRepository;

    public List<TieredPriceDTO> getAllTieredPrice(){
        return tieredPriceRepository.findAll().stream().map(
                tieredPrice -> new TieredPriceDTO(
                        tieredPrice.getTieredPriceId(),
                        tieredPrice.getDurationInDays(),
                        tieredPrice.getDiscountPercentage()
                )
        ).collect(Collectors.toList());
    }

    public Integer createTieredPrice(CreateTieredPriceRequestBody createTieredPriceRequestBody){
        TieredPrice tieredPrice = new TieredPrice();
        tieredPrice.setDiscountPercentage(createTieredPriceRequestBody.getDiscountPercentage());
        tieredPrice.setDurationInDays(createTieredPriceRequestBody.getDurationInDays());
        tieredPrice.setIsActive(Boolean.TRUE);
        TieredPrice savedTieredPrice = tieredPriceRepository.save(tieredPrice);
        return savedTieredPrice.getTieredPriceId();
    }

    public Integer disableTieredPrice(Integer tieredPriceId) throws ResourceNotFoundException {
        TieredPrice tieredPrice = tieredPriceRepository.findById(tieredPriceId)
            .orElseThrow(() -> new ResourceNotFoundException("Tiered price"));
        tieredPrice.setIsActive(Boolean.FALSE);
        tieredPriceRepository.save(tieredPrice);
        return tieredPrice.getTieredPriceId();
    }

}
