package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.BookingDto;
import com.cfs.BookMyShow.dto.BookingRequestDto;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.exception.SeatUnavailableException;
import com.cfs.BookMyShow.model.*;
import com.cfs.BookMyShow.repository.ShowRepository;
import com.cfs.BookMyShow.repository.ShowSeatRepository;
import com.cfs.BookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public BookingDto createBooking(BookingRequestDto bookingRequest){
        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        Show show = showRepository.findById(bookingRequest.getShowId())
                .orElseThrow(()-> new ResourceNotFoundException("Show not found"));

        List<ShowSeat> selectedSeats = showSeatRepository.findAllById(bookingRequest.getSeatIds());

        for(ShowSeat seat: selectedSeats){
            if(!"AVAILABLE".equals(seat.getStatus())){
                throw new SeatUnavailableException("Seat with id "+ seat.getSeat().getSeatNumber() +" is not available");
            }
            seat.setStatus("LOCKED");
        }
        showSeatRepository.saveAll(selectedSeats);

        Double totalAmount = selectedSeats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();

        //payment generated
        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(bookingRequest.getPaymentMethod());
        payment.setStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());

        //booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setBookingNumber(UUID.randomUUID().toString());

    }
}
