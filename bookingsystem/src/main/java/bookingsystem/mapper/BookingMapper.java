package com.moldovan.uni.bookingsystem.mapper;

import com.moldovan.uni.bookingsystem.domain.Booking;
import com.moldovan.uni.bookingsystem.dto.BookingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto mapToDto(Booking booking);
    Booking mapToEntity(BookingDto bookingDto);
}
