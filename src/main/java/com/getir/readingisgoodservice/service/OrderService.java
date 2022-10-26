package com.getir.readingisgoodservice.service;

import com.getir.readingisgoodservice.entity.Order;
import com.getir.readingisgoodservice.model.request.OrderCreateRequest;
import com.getir.readingisgoodservice.model.response.OrderCreateResponse;

import java.util.List;

public interface OrderService {
    OrderCreateResponse createOrder(OrderCreateRequest request);
    List<Order> getCustomerOrders(Long customerId);
}
