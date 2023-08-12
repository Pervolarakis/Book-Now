package com.example.Book.now.service;

import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.BookingRepository;
import com.example.Book.now.responseBodies.BookingDTO;
import com.example.Book.now.responseBodies.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingDTO getBookingById(Integer bookingId) throws ResourceNotFoundException {
        return bookingRepository.findById(bookingId)
            .map(booking -> new BookingDTO(
                booking.getBookingId(),
                booking.getBookingCreationDate(),
                new VehicleDTO(
                    booking.getVehicleId().getVehicleId(),
                    booking.getVehicleId().getName(),
                    booking.getVehicleId().getBrand(),
                    booking.getVehicleId().getVehicleYear(),
                    booking.getVehicleId().getVehicleType(),
                    booking.getVehicleId().getNumOfSeats(),
                    booking.getVehicleId().getMileage(),
                    booking.getVehicleId().getTransmission(),
                    booking.getVehicleId().getFuel(),
                    booking.getVehicleId().getNumOfBags(),
                    booking.getVehicleId().getNumOfDoors(),
                    booking.getVehicleId().getAc(),
                    booking.getVehicleId().getPhoto()
                ),
                booking.getQuantity(),
                booking.getPickupDate(),
                booking.getDeliveryDate(),
                booking.getCustomerId().getUserId(),
                booking.getPickupLocationId().getStoreId()
            ))
        .orElseThrow(() -> new ResourceNotFoundException("Booking"));
    }

}
