package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.dto.RoomDto;
import com.moldovan.uni.bookingsystem.mapper.RoomMapper;
import com.moldovan.uni.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomMapper roomMapper;

    public RoomDto save(RoomDto roomDto){
        Room room = roomMapper.mapToEntity(roomDto);
        roomRepository.save(room);
        return roomMapper.mapToDto(room);
    }
    public RoomDto update(RoomDto roomDto, Long roomId) {
        Room entity = roomRepository.getOne(roomId);
        entity.setCapacity(roomDto.getCapacity());
        entity.setHasView(roomDto.isHasView());
        roomRepository.save(entity);
        return roomDto;
    }

    public List<RoomDto> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        Room entity = roomRepository.getOne(id);
        roomRepository.delete(entity);
        return true;
    }

}
