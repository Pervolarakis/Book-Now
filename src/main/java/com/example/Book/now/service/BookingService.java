package com.example.Book.now.service;

import com.example.Book.now.Entities.*;
import com.example.Book.now.RequestBodies.CreateBookingRequestBody;
import com.example.Book.now.RequestBodies.CreateVehicleRequestBody;
import com.example.Book.now.RequestBodies.UpdateBookingRequestBody;
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
        BookingDTO booking = bookingRepository.findById(bookingId)
            .map(bookingItem -> new BookingDTO(
                bookingItem.getBookingId(),
                bookingItem.getBookingCreationDate(),
                new VehicleDTO(
                    bookingItem.getVehicleId().getVehicleId(),
                    bookingItem.getVehicleId().getName(),
                    bookingItem.getVehicleId().getBrand(),
                    bookingItem.getVehicleId().getVehicleYear(),
                    bookingItem.getVehicleId().getVehicleType(),
                    bookingItem.getVehicleId().getNumOfSeats(),
                    bookingItem.getVehicleId().getMileage(),
                    bookingItem.getVehicleId().getTransmission(),
                    bookingItem.getVehicleId().getFuel(),
                    bookingItem.getVehicleId().getNumOfBags(),
                    bookingItem.getVehicleId().getNumOfDoors(),
                    bookingItem.getVehicleId().getAc(),
                    bookingItem.getVehicleId().getPhoto()
                ),
                    bookingItem.getQuantity(),
                    bookingItem.getPickupDate(),
                    bookingItem.getDeliveryDate(),
                    bookingItem.getCustomerId().getUserId(),
                    bookingItem.getPickupLocationId().getStoreId()
            ))
        .orElseThrow(() -> new ResourceNotFoundException("Booking"));
        if (!userAccount.getRole().equals(RoleEnum.SCOPE_ROLE_ADMIN)&&!userAccount.getUserId().equals(booking.customerId())){
            throw new NotPermittedException();
        }
        return booking;
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

    public List<BookingDTO> getBookingsByUserId(Integer userId, String userMail) throws NotPermittedException {
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailIgnoreCase(userMail)
            .orElseThrow(() -> new UserDoesntExistsException());
        if (!userAccount.getRole().equals(RoleEnum.SCOPE_ROLE_ADMIN)&&!userAccount.getUserId().equals(userId)){
            throw new NotPermittedException();
        }
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

    public Integer updateBookingById(UpdateBookingRequestBody updateBookingRequestBody, Integer bookingId, String userMail) throws ResourceNotFoundException, NotPermittedException {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking"));
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailIgnoreCase(userMail)
            .orElseThrow(() -> new UserDoesntExistsException());
        if (!userAccount.getRole().equals(RoleEnum.SCOPE_ROLE_ADMIN)&&!userAccount.getUserId().equals(booking.getCustomerId().getUserId())){
            throw new NotPermittedException();
        }
        Vehicle vehicle = vehicleRepository.findById(updateBookingRequestBody.getVehicleId())
            .orElseThrow(()-> new ResourceNotFoundException("Vehicle"));
        booking.setVehicleId(vehicle);
        booking.setPickupDate(updateBookingRequestBody.getPickupDate());
        booking.setDeliveryDate(updateBookingRequestBody.getDeliveryDate());
        booking.setQuantity(updateBookingRequestBody.getQuantity());
        StoreLocation storeLocation = storeLocationRepository.findById(updateBookingRequestBody.getPickupLocationId())
            .orElseThrow(()-> new ResourceNotFoundException("Location"));
        booking.setPickupLocationId(storeLocation);
        bookingRepository.save(booking);
        return booking.getBookingId();
    }
}
