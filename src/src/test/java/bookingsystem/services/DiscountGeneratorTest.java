package com.moldovan.uni.bookingsystem.services;

import com.moldovan.uni.bookingsystem.service.DiscountGenerator;
import com.moldovan.uni.bookingsystem.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

public class DiscountGeneratorTest {

    @Test
    public void shouldGenerateCorrectDiscount(){
        RoomService roomService = Mockito.mock(RoomService.class);
        Mockito.when(roomService.getRoomCount()).thenReturn(4);
        DiscountGenerator discountGenerator = new DiscountGenerator(roomService);

        //ACT
        int actualDiscount = discountGenerator.generateDiscount(3);
        //Assert
        Assert.isTrue(actualDiscount == 15,"Actual discount should be 15 but it is : " + actualDiscount );
    }
}