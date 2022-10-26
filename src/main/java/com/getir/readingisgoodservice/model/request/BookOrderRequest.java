package com.getir.readingisgoodservice.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@Builder
public class BookOrderRequest {
    @NotNull
    private Long bookId;
    @Min(value = 1, message = "You can not order less than 1.")
    private Long orderCount;
}
