package com.getir.readingisgoodservice.controller;

import com.getir.readingisgoodservice.model.ApiResponse;
import com.getir.readingisgoodservice.model.response.StatisticsResponse;
import com.getir.readingisgoodservice.model.response.StockResponse;
import com.getir.readingisgoodservice.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("{customerId}/")
    public ApiResponse<StatisticsResponse> getStatistics(@PathVariable Long customerId) {
        log.info("getStatistics called with: {}", customerId);
        StatisticsResponse statisticsResponse = statisticsService.getStatistics(customerId);
        return ApiResponse.<StatisticsResponse>builder()
                .data(statisticsResponse)
                .status("0")
                .message("success")
                .build();
    }
}
