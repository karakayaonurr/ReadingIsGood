package com.getir.readingisgoodservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
    private Long bookId;
    private String bookName;
    private Long remainingStock;
}
