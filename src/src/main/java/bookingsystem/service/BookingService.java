package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.Booking;
import com.moldovan.uni.bookingsystem.domain.ExtraService;
import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.dto.BookingDto;
import com.moldovan.uni.bookingsystem.mapper.BookingMapper;
import com.moldovan.uni.bookingsystem.repository.BookingRepository;
import com.moldovan.uni.bookingsystem.repository.ExtraServiceRepository;
import com.moldovan.uni.bookingsystem.repository.PersonRepository;
import com.moldovan.uni.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BookingMapper bookingMapper;

    public BookingDto save(BookingDto bookingDto) {
        Booking booking = bookingMapper.mapToEntity(bookingDto);
        Booking savedBooking = bookingRepository.save(booking);
        BookingDto created = bookingMapper.mapToDto(savedBooking);
        created.setGetUrl("http://localhost:8081/api/booking/"+savedBooking.getId());
        return created;
    }

    public BookingDto update(BookingRequest bookingRequest, Long bookingId) {
        Booking currentEntity = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking with id : "+ bookingId +" does not exist in the database"));
        currentEntity.setCheckInDate(bookingRequest.getCheckInDate());
        currentEntity.setCheckOutDate(bookingRequest.getCheckOutDate());
        Set<Room> reservedRooms = roomRepository.findAll().stream().filter(r ->bookingRequest.getRoomIds().contains(r.getId())).collect(Collectors.toSet());
        currentEntity.setReservedRooms( reservedRooms);
        Set<ExtraService> extraServices = extraServiceRepository.findAll().stream().filter(r ->bookingRequest.getServiceIds().contains(r.getId())).collect(Collectors.toSet());
        currentEntity.setExtraServices(extraServices);
        Optional<Person> responsiblePerson = personRepository.findById(bookingRequest.getResponsiblePersonId());
        currentEntity.setResponsiblePerson(responsiblePerson.orElse(null));
        Booking updated = bookingRepository.save(currentEntity);

        return bookingMapper.mapToDto(updated);
    }

    public List<BookingDto> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Booking entity = bookingRepository.getOne(id);
        bookingRepository.delete(entity);
    }
    public BookingDto get(Long bookingId) {
        return bookingMapper.mapToDto(bookingRepository.findById(bookingId).get());
    }

    public BookingDto register(BookingRequest bookingRequest) {

        Set<Room> reservedRooms = roomRepository.findAll().stream().filter(r ->bookingRequest.getRoomIds().contains(r.getId())).collect(Collectors.toSet());
        checkIfRoomsAreAvailable(reservedRooms, bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new IllegalArgumentException("CheckInDate needs to be before CheckOutDate :" + bookingRequest);
        }
        Booking booking = new Booking();
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        booking.setReservedRooms(reservedRooms);
        Set<ExtraService> extraServices = extraServiceRepository.findAll().stream().filter(r ->bookingRequest.getServiceIds().contains(r.getId())).collect(Collectors.toSet());
        booking.setExtraServices(extraServices);
        Optional<Person> responsiblePerson = personRepository.findById(bookingRequest.getResponsiblePersonId());
        booking.setResponsiblePerson(responsiblePerson.orElse(null));
        Booking saved = bookingRepository.save(booking);
        BookingDto created = bookingMapper.mapToDto(saved);
        created.setGetUrl("http://localhost:8081/api/booking/" + saved.getId());
        return created;
    }

    private void checkIfRoomsAreAvailable(Set<Room> reservedRooms, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Booking> bookings = bookingRepository.findAll().stream().filter(b -> ((b.getCheckInDate().isBefore(checkInDate) && b.getCheckOutDate().isAfter(checkOutDate) )
                || (b.getCheckInDate().isAfter(checkInDate) && b.getCheckInDate().isBefore(checkOutDate)) || (b.getCheckOutDate().isAfter(checkInDate) && b.getCheckOutDate().isBefore(checkOutDate)))).collect(Collectors.toList());
        Set<Room> allReadyBookedRooms = new HashSet<>();
        for (Booking booking : bookings){
            allReadyBookedRooms.addAll(booking.getReservedRooms());
        }
        reservedRooms.forEach((room -> {
            if (allReadyBookedRooms.contains(room)){
                throw new RoomAllreadyBookedException("Room with id :" + room.getId() + " is allready booked between : " + checkInDate +" and " + checkOutDate);
            }
        }));
    }
}