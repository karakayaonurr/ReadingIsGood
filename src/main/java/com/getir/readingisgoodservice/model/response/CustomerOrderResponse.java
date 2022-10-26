package com.getir.readingisgoodservice.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CustomerOrderResponse {
    CustomerResponse customer;
    List<OrderDetailResponse> orders;
}
