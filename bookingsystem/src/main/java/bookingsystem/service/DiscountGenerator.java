package com.moldovan.uni.bookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DiscountGenerator {
    private final RoomService roomService;

    @Autowired
    public DiscountGenerator(RoomService roomService) {
        this.roomService = roomService;
    }

    public int generateDiscount( int numberOfRoomsBooked){
        int totalPossibleRooms = roomService.getRoomCount();
        float occupancyRate = (float)numberOfRoomsBooked / (float)totalPossibleRooms;
        if (occupancyRate < 0.7){
            return 0;
        }
        return (int) ((0.4-(1-occupancyRate))*100);
    }
}
