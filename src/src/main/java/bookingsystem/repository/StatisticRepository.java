package com.moldovan.uni.bookingsystem.repository;

import com.moldovan.uni.bookingsystem.domain.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic,Long> {
}