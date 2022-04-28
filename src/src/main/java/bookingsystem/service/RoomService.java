package bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.dto.PersonDto;
import com.moldovan.uni.bookingsystem.dto.RoomDto;
import com.moldovan.uni.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public RoomDto save(RoomDto roomDto){
        Room room = mapToEntity(roomDto);
        roomRepository.save(room);
        return mapToDto(room);
    }
    public RoomDto update(RoomDto roomDto) {
        Room update = roomRepository.update(mapToEntity(roomDto));
        if (null != update) {
            return mapToDto(update);
        }
        return null;
    }

    public List<RoomDto> getAll() {
        return roomRepository.getAll()
                .stream()
                .map(room -> mapToDto(room))
                .collect(Collectors.toList());
    }

    public boolean delete(int id) {
        Optional<Room> optionalRoom = roomRepository.getAll()
                .stream()
                .filter(room -> room.getRoomId() == id)
                .findAny();
        if (optionalRoom.isPresent()) {
            roomRepository.delete(optionalRoom.get());
            return true;
        }
        return false;
    }
    private RoomDto mapToDto(Room room) {
        return new RoomDto(room.getRoomId(),room.getCapacity(),room.isHasView());
    }

    private Room mapToEntity(RoomDto roomDto) {
        int currentMaxId = roomRepository.getMaxId().orElse(0);
        return new Room(currentMaxId + 1,2,true);
    }

}
