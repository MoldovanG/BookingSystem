package bookingsystem.controller;

import com.moldovan.uni.bookingsystem.dto.RoomDto;
import com.moldovan.uni.bookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDto> getAll() {
        return roomService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto create(@RequestBody RoomDto roomDto) {
        return roomService.save(roomDto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@PathVariable("id") int id) {
        boolean result = roomService.delete(id);
        return result ? String.format("Room %s was removed", id)
                : String.format("Room %s was not removed", id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDto> update(@RequestBody RoomDto roomDto) {
        RoomDto updatedRoom = roomService.update(roomDto);
        return new ResponseEntity<>(updatedRoom, null == updatedRoom ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
