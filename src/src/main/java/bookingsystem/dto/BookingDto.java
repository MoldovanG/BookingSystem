package bookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class Booking {

    private String bookingId;
    private Person responsiblePerson;
    private List<Room> reservedRooms;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
