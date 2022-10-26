package com.getir.readingisgoodservice.service;

import com.getir.readingisgoodservice.model.response.StatisticsResponse;

public interface StatisticsService {
    StatisticsResponse getStatistics(Long customerId);
}
