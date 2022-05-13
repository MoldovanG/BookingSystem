package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.domain.Statistic;
import com.moldovan.uni.bookingsystem.dto.StatisticDto;
import com.moldovan.uni.bookingsystem.mapper.StatisticMapper;
import com.moldovan.uni.bookingsystem.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;
    private final BookingRegistrationService bookingRegistrationService;
    private final RoomService roomService;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository, StatisticMapper statisticMapper, BookingRegistrationService bookingRegistrationService, RoomService roomService) {
        this.statisticRepository = statisticRepository;
        this.statisticMapper = statisticMapper;
        this.bookingRegistrationService = bookingRegistrationService;
        this.roomService = roomService;
    }

    public List<StatisticDto> getAll() {
        return statisticRepository.findAll()
                .stream()
                .map(statisticMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public StatisticDto generateStatistic(LocalDate start, LocalDate end){
        Statistic statistic = new Statistic();
        statistic.setEndDate(end);
        statistic.setStartDate(start);
        Set<Room> bookedRooms = bookingRegistrationService.getBookedRooms(start,end);
        statistic.setOccupancyRate(calculateOcupancyRate(bookedRooms, start, end));
        statistic.setTotalIncome(calculateTotalIncome(bookedRooms));
        statistic.setNumberOfRoomsBooked(bookedRooms.size());
        return statisticMapper.mapToDto(statisticRepository.save(statistic));
    }

    private int calculateTotalIncome(Set<Room> bookedRooms) {
        return bookedRooms.stream().mapToInt(b -> b.getPrice()).sum();
    }

    private int calculateOcupancyRate(Set<Room> bookedRooms, LocalDate start, LocalDate end) {
        int totalRoomsAvailable = roomService.getRoomCount();
        int bookedRoomsCount = bookedRooms.size();
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(start),
                YearMonth.from(end)
        ) + 1;
        return (int)(100 * bookedRoomsCount / (totalRoomsAvailable * monthsBetween));
    }
}