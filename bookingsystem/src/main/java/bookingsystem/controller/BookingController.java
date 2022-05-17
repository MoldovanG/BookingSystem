package com.moldovan.uni.bookingsystem.controller;

import com.moldovan.uni.bookingsystem.dto.BookingDto;
import com.moldovan.uni.bookingsystem.service.BookingRegistrationService;
import com.moldovan.uni.bookingsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingRegistrationService bookingRegistrationService;
    @Autowired
    private EmailService emailService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingDto> getAll() {
        return bookingRegistrationService.getAll();
    }

    @GetMapping(value = "/paginated", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<BookingDto> getPaginated(@RequestParam(name= "page") int page, @RequestParam(name= "limit") int limitPerPage) {
        PageRequest pageRequest = PageRequest.of(page,limitPerPage);
        return bookingRegistrationService.getPaginated(pageRequest);
    }

    @GetMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto get(@PathVariable("bookingId") Long bookingId) {
        return bookingRegistrationService.get(bookingId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto registerBooking(
            @Valid
            @RequestBody BookingRequest bookingRequest) {
        BookingDto registered = bookingRegistrationService.register(bookingRequest);
        emailService.sendSimpleMessage(registered.getInvoice().getResponsiblePerson().getEmail(),"Confirmation of your Reservation", "We are pleased to announce you that your booking has been confirmed for the following period : " + registered.getCheckInDate() + " until " + registered.getCheckOutDate() + "\n"
                + "You can access your booking details at the following url : " + registered.getGetUrl());
        return registered;
    }

    @DeleteMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@PathVariable("bookingId") Long bookingId) {
        bookingRegistrationService.delete(bookingId);
        return String.format("Booking %s was removed", bookingId);
    }

    @PutMapping(value = "/{bookingId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDto> update(@RequestBody BookingRequest bookingRequest, @PathVariable("bookingId") Long bookingId) {
        BookingDto updatedBooking = bookingRegistrationService.update(bookingRequest, bookingId);
        return new ResponseEntity<>(updatedBooking, null == updatedBooking ? HttpStatus.NO_CONTENT: HttpStatus.OK);
    }

}