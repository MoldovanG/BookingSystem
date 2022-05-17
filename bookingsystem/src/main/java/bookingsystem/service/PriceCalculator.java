package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.ExtraService;
import com.moldovan.uni.bookingsystem.domain.Room;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PriceCalculator {

    public int calculatePrice(Set<Room> rooms, Set<ExtraService> extraServiceList){
        int totalPrice = 0;
        for(Room room : rooms){
            totalPrice += room.getPrice();
        }
        for(ExtraService extraService : extraServiceList){
            totalPrice += extraService.getCost();
        }
        return totalPrice;
    }

}
