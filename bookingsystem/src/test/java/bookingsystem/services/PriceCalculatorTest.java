package com.moldovan.uni.bookingsystem.services;

import com.moldovan.uni.bookingsystem.domain.ExtraService;
import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.domain.ServiceType;
import com.moldovan.uni.bookingsystem.service.PriceCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class PriceCalculatorTest {

    @Test
    public void shouldCalculateTheCorrectPrice(){
        //ARRANGE
        PriceCalculator priceCalculator = new PriceCalculator();
        Set<Room> rooms = new HashSet<>();
        rooms.add(Room.builder().capacity(3).price(150).build());
        rooms.add(Room.builder().capacity(3).price(250).build());
        Set<ExtraService> extraServices = new HashSet<>();
        extraServices.add(ExtraService.builder().cost(100).type(ServiceType.ExtraCleaning).build());
        extraServices.add(ExtraService.builder().cost(200).type(ServiceType.DogCompanion).build());

        int expectedPrice = 700;
        //ACT
        int actualPrice = priceCalculator.calculatePrice(rooms,extraServices);
        //ASSERT
        Assert.isTrue(actualPrice == expectedPrice);

    }
}