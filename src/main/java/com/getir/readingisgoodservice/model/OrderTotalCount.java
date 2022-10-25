package com.getir.readingisgoodservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
public class OrderTotalCount {
    private long totalBook;
    private BigDecimal totalAmount;
}
