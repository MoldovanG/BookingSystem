package com.moldovan.uni.bookingsystem.repository;

import com.moldovan.uni.bookingsystem.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}