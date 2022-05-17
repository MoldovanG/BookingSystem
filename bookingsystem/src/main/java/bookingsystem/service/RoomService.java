package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.dto.RoomDto;
import com.moldovan.uni.bookingsystem.mapper.RoomMapper;
import com.moldovan.uni.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomMapper roomMapper;

    public RoomDto create(RoomDto roomDto){
        Room room = roomMapper.mapToEntity(roomDto);
        roomRepository.save(room);
        return roomMapper.mapToDto(room);
    }
    public RoomDto update(RoomDto roomDto, Long roomId) {
        Room entity = roomRepository.findById(roomId).get();
        entity.setCapacity(roomDto.getCapacity());
        entity.setHasView(roomDto.isHasView());
        entity.setPrice(roomDto.getPrice());
        roomRepository.save(entity);
        return roomDto;
    }
    public int getRoomCount(){
        return roomRepository.findTotalCount();
    }

    public RoomDto get(Long id){
        return roomMapper.mapToDto(roomRepository.findById(id).get());
    }
    public List<RoomDto> getAll() {
        return  StreamSupport.stream(roomRepository.findAll(Sort.by("id")).spliterator(),false)
                .map(roomMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Page<RoomDto> getPaginated(PageRequest pageRequest){
        return roomRepository.findAll(pageRequest).map(roomMapper::mapToDto);
    }

    public boolean delete(Long id) {
        Room entity = roomRepository.findById(id).get();
        roomRepository.delete(entity);
        return true;
    }

}
