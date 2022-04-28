package com.moldovan.uni.bookingsystem.dto;

import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.domain.Room;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @NotNull
    private Person responsiblePerson;
    @NotNull
    private List<Room> reservedRooms;
    @FutureOrPresent
    private LocalDate checkInDate;
    @Future
    private LocalDate checkOutDate;
}
