package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.request_body.RevenueStatisticsDTO;
import com.ptit.webserviceelectronicshop.service.RevenueStatisticsService;
import com.ptit.webserviceelectronicshop.service.implement.RevenueStatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/revenue")
public class StatisticsController {
    @Autowired
    private RevenueStatisticsServiceImpl revenueStatisticsService;

    @GetMapping("/statistics")
    //GET /api/revenue/statistics?start=2023-01-01&end=2023-05-01
    public List<RevenueStatisticsDTO> getRevenueStatisticsByDateRange(
            @RequestParam("start") @DateTimeFormat(pattern = "dd/MM/yyyy-HH:mm") LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(pattern = "dd/MM/yyyy-HH:mm") LocalDateTime end) {
        return revenueStatisticsService.getRevenueStatisticsByDateRange(start, end);
    }
}
