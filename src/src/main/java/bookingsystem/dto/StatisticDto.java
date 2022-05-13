package com.moldovan.uni.bookingsystem.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfRoomsBooked;
    private int occupancyRate;
    private int totalIncome;
}