package com.moldovan.uni.bookingsystem.exception.advice;

import com.moldovan.uni.bookingsystem.exceptions.RoomRegistrationUnavailableException;
import com.moldovan.uni.bookingsystem.service.RoomAllreadyBookedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<String> handle(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(LocalTime.now() +  "- Entity was not found and failed with message :  " + e.getMessage());
    }

    @ExceptionHandler(RoomAllreadyBookedException.class)
    public ResponseEntity<String> handle(RoomAllreadyBookedException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(LocalTime.now() +  "- Booking was not registered. One of the rooms is already booked for the selected period :  " + e.getMessage());
    }

    @ExceptionHandler(RoomRegistrationUnavailableException.class)
    public ResponseEntity<String> handle(RoomRegistrationUnavailableException e){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(LocalTime.now() +  "- Room registration currently unavailable ");
    }

}
