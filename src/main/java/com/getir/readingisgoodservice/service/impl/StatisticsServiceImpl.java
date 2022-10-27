package com.getir.readingisgoodservice.service.impl;

import com.getir.readingisgoodservice.entity.Order;
import com.getir.readingisgoodservice.exception.ApiErrorType;
import com.getir.readingisgoodservice.exception.StatisticsNotFoundException;
import com.getir.readingisgoodservice.model.response.StatisticsResponse;
import com.getir.readingisgoodservice.service.OrderService;
import com.getir.readingisgoodservice.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import static com.getir.readingisgoodservice.exception.ApiErrorType.STATISTICS_NOT_FOUND_EXCEPTION;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toStatisticsResponse;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderService orderService;

    @Override
    public StatisticsResponse getStatistics(Long customerId) {
        List<Order> orders = orderService.getCustomerOrders(customerId);

        if(orders.isEmpty()) {
            throw new StatisticsNotFoundException(STATISTICS_NOT_FOUND_EXCEPTION);
        }

        Map<YearMonth, List<Order>> orderMap = orders.stream()
                .collect(groupingBy(order -> {
                    LocalDate localDate = order.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
                    return yearMonth;
                }));

        return toStatisticsResponse(orderMap);
    }
}
