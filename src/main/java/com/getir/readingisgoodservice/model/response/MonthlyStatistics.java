package com.getir.readingisgoodservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

@Builder
@Getter
@Setter
@ToString
public class MonthlyStatistics {
    private int year;
    private Month month;
    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalPurchasedAmount;
}
