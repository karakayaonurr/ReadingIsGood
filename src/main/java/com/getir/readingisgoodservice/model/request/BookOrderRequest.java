package com.getir.readingisgoodservice.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BookOrderRequest {
    private Long bookId;
    private Long orderCount;
}
