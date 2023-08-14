package com.example.Book.now.service;

import com.example.Book.now.Entities.Booking;
import com.example.Book.now.Entities.BookingStatus;
import com.example.Book.now.Entities.RoleEnum;
import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.RequestBodies.BookingStatusRequestBody;
import com.example.Book.now.exceptions.NotPermittedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.BookingRepository;
import com.example.Book.now.repository.BookingStatusRepository;
import com.example.Book.now.repository.UserAccountRepository;
import com.example.Book.now.responseBodies.BookingStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingStatusService {

    private final UserAccountRepository userAccountRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final BookingRepository bookingRepository;

    public List<BookingStatusDTO> getBookingStatusByBookingId(Integer bookingId, String userMail) throws ResourceNotFoundException, NotPermittedException {

        List<BookingStatus> bookingStatusList = bookingStatusRepository.findByBookingIdBookingIdOrderByStatusDate(bookingId);

        UserAccount userAccount = userAccountRepository.findUserAccountByEmailIgnoreCase(userMail)
            .orElseThrow(()-> new ResourceNotFoundException("User"));

        if (bookingStatusList.size() == 0){
            return new ArrayList<BookingStatusDTO>();
        }

        if (!userAccount.getRole().equals(RoleEnum.SCOPE_ROLE_ADMIN) && !userAccount.getUserId().equals(bookingStatusList.get(0).getBookingId().getCustomerId().getUserId())){
            throw new NotPermittedException();
        }

        return bookingStatusList.stream().map(
            booking -> new BookingStatusDTO(
                booking.getBookingStatusId(),
                booking.getBookingId(),
                booking.getStatusDate(),
                booking.getStatus())
        ).collect(Collectors.toList());

    }

    public Integer createBookingStatus(Integer bookingId, BookingStatusRequestBody bookingStatusRequestBody) throws ResourceNotFoundException {

        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking"));

        BookingStatus bookingStatus = new BookingStatus();

        bookingStatus.setBookingId(booking);
        bookingStatus.setStatus(bookingStatusRequestBody.getStatus());
        bookingStatus.setStatusDate(new Date());

        BookingStatus bookingStatusSaved = bookingStatusRepository.save(bookingStatus);

        return bookingStatusSaved.getBookingStatusId();

    }

}
