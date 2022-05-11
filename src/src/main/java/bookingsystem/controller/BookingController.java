package com.moldovan.uni.bookingsystem.controller;

import com.moldovan.uni.bookingsystem.domain.Booking;
import com.moldovan.uni.bookingsystem.dto.BookingDto;
import com.moldovan.uni.bookingsystem.service.BookingRequest;
import com.moldovan.uni.bookingsystem.service.BookingService;
import com.moldovan.uni.bookingsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private EmailService emailService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingDto> getAll() {
        return bookingService.getAll();
    }

    @GetMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto get(@PathVariable("bookingId") Long bookingId) {
        return bookingService.get(bookingId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto registerBooking(
            @Valid
            @RequestBody BookingRequest bookingRequest) {
        BookingDto registered = bookingService.register(bookingRequest);
        emailService.sendSimpleMessage(registered.getResponsiblePerson().getEmail(),"Confirmation of your Reservation", "We are pleased to announce you that your booking has been confirmed for the following period : " + registered.getCheckInDate() + " until " + registered.getCheckOutDate());
        return registered;
    }

    @DeleteMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@PathVariable("bookingId") Long bookingId) {
        bookingService.delete(bookingId);
        return String.format("Booking %s was removed", bookingId);
    }

    @PutMapping(value = "/{bookingId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDto> update(@RequestBody BookingRequest bookingRequest, @PathVariable("bookingId") Long bookingId) {
        BookingDto updatedBooking = bookingService.update(bookingRequest, bookingId);
        return new ResponseEntity<>(updatedBooking, null == updatedBooking ? HttpStatus.NO_CONTENT: HttpStatus.OK);
    }

}