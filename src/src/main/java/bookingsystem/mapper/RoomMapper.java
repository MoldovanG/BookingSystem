package com.moldovan.uni.bookingsystem.mapper;

import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.dto.RoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto mapToDto(Room room);
    Room mapToEntity(RoomDto roomDto);
}
