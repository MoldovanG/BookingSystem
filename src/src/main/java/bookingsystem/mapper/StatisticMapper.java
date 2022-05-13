package com.moldovan.uni.bookingsystem.mapper;

import com.moldovan.uni.bookingsystem.domain.Statistic;
import com.moldovan.uni.bookingsystem.dto.StatisticDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapper {
    StatisticDto mapToDto(Statistic statistic);
}