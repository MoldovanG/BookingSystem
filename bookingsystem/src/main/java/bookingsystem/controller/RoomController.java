package com.moldovan.uni.bookingsystem.controller;

import com.moldovan.uni.bookingsystem.config.FeatureFlagDefaultConfig;
import com.moldovan.uni.bookingsystem.dto.RoomDto;
import com.moldovan.uni.bookingsystem.exceptions.RoomRegistrationUnavailableException;
import com.moldovan.uni.bookingsystem.service.FeatureFlagServiceProxy;
import com.moldovan.uni.bookingsystem.service.RoomService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private FeatureFlagDefaultConfig configuration;
    @Autowired
    private FeatureFlagServiceProxy featureFlagServiceProxy;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDto> getAll() {
        return roomService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto getRoom(@PathVariable ("id") Long id) {
        return roomService.get(id);
    }

    @GetMapping(value = "/paginated", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<RoomDto> getPaginated(@RequestParam(name= "page") int page, @RequestParam(name= "limit") int limitPerPage) {
        PageRequest pageRequest = PageRequest.of(page,limitPerPage);
        return roomService.getPaginated(pageRequest);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(ignoreExceptions = { RoomRegistrationUnavailableException.class},fallbackMethod = "fallbackRegisterRoom")
    public RoomDto create(
            @Valid
            @RequestBody RoomDto roomDto) {
        if(featureFlagServiceProxy.findFeatureFlag("enableRegisterRoom").isValue())
        {
            return roomService.create(roomDto);
        } else{
           throw new RoomRegistrationUnavailableException();
        }
    }

    public RoomDto fallbackRegisterRoom(
            @Valid
            @RequestBody RoomDto roomDto) {
        if (configuration.getEnableRegisterRoom() > 0)
        {
            return roomService.create(roomDto);
        } else{
            throw new RoomRegistrationUnavailableException();
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@PathVariable("id") Long id) {
        boolean result = roomService.delete(id);
        return result ? String.format("Room %s was removed", id)
                : String.format("Room %s was not removed", id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDto> update(@RequestBody RoomDto roomDto, @PathVariable("id") Long id) {
        RoomDto updatedRoom = roomService.update(roomDto, id);
        return new ResponseEntity<>(updatedRoom, null == updatedRoom ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
