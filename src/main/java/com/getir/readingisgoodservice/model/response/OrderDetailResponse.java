package com.getir.readingisgoodservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
@Builder
public class OrderDetailResponse {
    List<BookDetailResponse> books;
    private Long totalBook;
    private BigDecimal totalPrice;
}
