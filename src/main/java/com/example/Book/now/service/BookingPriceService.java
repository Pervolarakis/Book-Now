package com.example.Book.now.service;

import com.example.Book.now.Entities.TieredPrice;
import com.example.Book.now.exceptions.VehicleNotAvailableException;
import com.example.Book.now.repository.CouponRepository;
import com.example.Book.now.repository.PriceRepository;
import com.example.Book.now.repository.TieredPriceRepository;
import com.example.Book.now.responseBodies.BookingPriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BookingPriceService {

    private final PriceRepository priceRepository;
    private final CouponRepository couponRepository;
    private final TieredPriceRepository tieredPriceRepository;

    public BookingPriceDTO calculateBookingCost(Integer vehicleId, Integer locationId, Date pickupDate, Date deliveryDate, String couponCode) throws VehicleNotAvailableException {
        LocalDate start = pickupDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = deliveryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer dayCnt = 0;
        Float totalPrice = 0.00f;
        Float totalPriceBeforeDiscount = 0.00f;
        Float tieredPriceDiscount = 0.00f;
        Float couponDiscountPercentage = 0.00f;
        for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
            // Do your job here with `date`.
            dayCnt ++;
            if (priceRepository.findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(vehicleId, locationId, date, date).isPresent()) {
                totalPrice += priceRepository.findByVehicleIdVehicleIdAndStoreIdStoreIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(vehicleId, locationId, date, date).get().getPrice();
            } else {
                throw new VehicleNotAvailableException();
            }
        }
        totalPriceBeforeDiscount = totalPrice;

        if(tieredPriceRepository.findTopByDurationInDaysLessThanEqualOrderByDurationInDaysDesc(dayCnt).isPresent()){
            tieredPriceDiscount = tieredPriceRepository.findTopByDurationInDaysLessThanEqualOrderByDurationInDaysDesc(dayCnt).get().getDiscountPercentage();
            totalPrice = totalPrice - (0.01f * tieredPriceDiscount) * totalPrice;
        }
        if(couponRepository.findById(couponCode).isPresent()){
            couponDiscountPercentage = couponRepository.findById(couponCode).get().getDiscountPercentage();
            totalPrice = totalPrice - (0.01f * couponDiscountPercentage) * totalPrice;
        }
        return new BookingPriceDTO(
            totalPriceBeforeDiscount,
            totalPrice,
            tieredPriceDiscount,
            couponDiscountPercentage
        );
    }

}
