package com.getir.readingisgoodservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderCreateResponse {
    private CustomerResponse customer;
    private List<OrderResponse> orders;
}
