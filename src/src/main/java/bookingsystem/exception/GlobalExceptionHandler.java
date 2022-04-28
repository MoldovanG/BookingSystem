package com.moldovan.uni.bookingsystem.repository;

import com.moldovan.uni.bookingsystem.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepository {
    private List<Room> rooms = new ArrayList<>();

    public RoomRepository() {
        loadData();
    }

    public Optional<Long> getMaxId() {
        return rooms.stream().map(room -> room.getId()).max(Comparator.naturalOrder());
    }

    public Room save(Room room) {
        rooms.add(room);
        return room;
    }

    public Room update(Room room) {
        Optional<Room> optionalRoom = rooms.stream()
                .filter(roomIterator -> roomIterator.getId() == room.getId())
                .findAny();
        if (optionalRoom.isPresent()) {
            rooms.remove(optionalRoom.get());
            rooms.add(room);
            return room;
        }
        return null;
    }

    public List<Room> getAll() {
        return rooms;
    }

    public void delete(Room room) {
        rooms.remove(room);
    }

    private void loadData() {
        Room room1 = new Room(0l,2,true);
        Room room2 = new Room(1l,2,false);
        Room room3 = new Room(2l,3,true);
        Room room4 = new Room(3l,2,true);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
    }
}
