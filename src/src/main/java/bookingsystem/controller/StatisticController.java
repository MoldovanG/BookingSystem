package com.moldovan.uni.bookingsystem.controller;

import com.moldovan.uni.bookingsystem.dto.StatisticDto;
import com.moldovan.uni.bookingsystem.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StatisticDto> getAll() {
        return statisticService.getAll();
    }

    @PostMapping(value = "/{start-date}/{end-date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticDto generate(@PathVariable("start-date") String start, @PathVariable("end-date") String end) {
        return statisticService.generateStatistic(LocalDate.parse(start),LocalDate.parse(end));
    }
}