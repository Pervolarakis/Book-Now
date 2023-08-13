package com.example.Book.now.service;

import com.example.Book.now.Entities.*;
import com.example.Book.now.RequestBodies.CreateBookingRequestBody;
import com.example.Book.now.RequestBodies.CreateVehicleRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.exceptions.UserDoesntExistsException;
import com.example.Book.now.repository.BookingRepository;
import com.example.Book.now.repository.StoreLocationRepository;
import com.example.Book.now.repository.UserAccountRepository;
import com.example.Book.now.repository.VehicleRepository;
import com.example.Book.now.responseBodies.BookingDTO;
import com.example.Book.now.responseBodies.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserAccountRepository userAccountRepository;
    private final VehicleRepository vehicleRepository;
    private final StoreLocationRepository storeLocationRepository;

    public BookingDTO getBookingById(Integer bookingId, String userMail) throws ResourceNotFoundException, NotPermittedException {
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailIgnoreCase(userMail)
            .orElseThrow(() -> new UserDoesntExistsException());
        BookingDTO bookingFrom = bookingRepository.findById(bookingId)
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
        if (!userAccount.getRole().equals(RoleEnum.SCOPE_ROLE_ADMIN)&&!userAccount.getUserId().equals(bookingFrom.customerId())){
            throw new NotPermittedException();
        }
        return bookingFrom;
    }

    public List<BookingDTO> getBookingsByLocationId(Integer locationId){
        return bookingRepository.findByPickupLocationIdStoreId(locationId).stream()
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
            )).collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByUserId(Integer userId){
        return bookingRepository.findByCustomerIdUserId(userId)
            .stream().map(booking -> new BookingDTO(
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
            )).collect(Collectors.toList());
    }

    public List<BookingDTO> getAllBookings(){
        return bookingRepository.findAll()
            .stream().map(booking -> new BookingDTO(
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
            )).collect(Collectors.toList());
    }

    public Integer createBooking(CreateBookingRequestBody createBookingRequestBody, String userEmail) throws ResourceNotFoundException {
        Booking booking = new Booking();
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailIgnoreCase(userEmail)
            .orElseThrow(()-> new ResourceNotFoundException("User"));
        booking.setCustomerId(userAccount);
        booking.setBookingCreationDate(new Date());
        booking.setPickupDate(createBookingRequestBody.getPickupDate());
        booking.setDeliveryDate(createBookingRequestBody.getDeliveryDate());
        booking.setQuantity(createBookingRequestBody.getQuantity());
        Vehicle vehicle = vehicleRepository.findById(createBookingRequestBody.getVehicleId())
            .orElseThrow(()-> new ResourceNotFoundException("Vehicle"));
        booking.setVehicleId(vehicle);
        StoreLocation storeLocation = storeLocationRepository.findById(createBookingRequestBody.getPickupLocationId())
            .orElseThrow(()-> new ResourceNotFoundException("Location"));
        booking.setPickupLocationId(storeLocation);
        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking.getBookingId();
    }
}
