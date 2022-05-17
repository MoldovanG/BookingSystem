package com.moldovan.uni.bookingsystem.repository;

import com.moldovan.uni.bookingsystem.domain.Booking;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
}
