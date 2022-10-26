package com.getir.readingisgoodservice.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
@Builder
public class OrderCreateRequest {
    @NotNull
    private Long customerId;
    @NotEmpty(message = "Order list can not be empty.")
    private List<BookOrderRequest> bookOrderRequests;
}
