package com.moldovan.uni.bookingsystem.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statistic")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="start_date")
    private LocalDate startDate;

    @Column(name ="end_date")
    private LocalDate endDate;

    @Column(name ="number_of_rooms_booked")
    private int numberOfRoomsBooked;
    @Column(name ="occupancy_rate")
    private int occupancyRate;

    @Column(name= "total_income")
    private int totalIncome;

}