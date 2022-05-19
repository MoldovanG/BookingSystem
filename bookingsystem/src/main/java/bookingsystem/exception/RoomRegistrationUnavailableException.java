package com.moldovan.uni.bookingsystem.exceptions;

public class RoomRegistrationUnavailableException extends RuntimeException {
    public RoomRegistrationUnavailableException(String message) {
        super(message);
    }
    public RoomRegistrationUnavailableException() {
        super();
    }
}