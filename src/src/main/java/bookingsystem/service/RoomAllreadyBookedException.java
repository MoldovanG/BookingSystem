package com.moldovan.uni.bookingsystem.service;

public class RoomAllreadyBookedException extends RuntimeException {

    public RoomAllreadyBookedException(String message) {
        super(message);
    }
}