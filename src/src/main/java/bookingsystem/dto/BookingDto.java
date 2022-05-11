package com.moldovan.uni.bookingsystem.dto;

import com.moldovan.uni.bookingsystem.domain.ExtraService;
import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.domain.Room;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @NotNull
    private Person responsiblePerson;
    @NotNull
    private Set<Room> reservedRooms;
    @NotNull
    private Set<ExtraService> extraServices;
    @FutureOrPresent
    private LocalDate checkInDate;
    @Future
    private LocalDate checkOutDate;

    private String getUrl;
}
