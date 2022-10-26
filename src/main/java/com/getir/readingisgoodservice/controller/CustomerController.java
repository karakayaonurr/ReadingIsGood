package com.getir.readingisgoodservice.controller;

import com.getir.readingisgoodservice.model.ApiResponse;
import com.getir.readingisgoodservice.model.request.CustomerRequest;
import com.getir.readingisgoodservice.model.response.CustomerOrderResponse;
import com.getir.readingisgoodservice.model.response.CustomerResponse;
import com.getir.readingisgoodservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/customer")
public class CustomerController
{
    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request)
    {
        log.info("Create customer called with request: {}", request);
        CustomerResponse response = customerService.createCustomer(request);
        return ApiResponse.<CustomerResponse>builder()
                .data(response)
                .status("OK")
                .message("successful")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerResponse> getCustomerById(@PathVariable Long id)
    {
        log.info("getCustomerById called with: {}", id);
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return ApiResponse.<CustomerResponse>builder()
                .data(customerResponse)
                .status("0")
                .message("success")
                .build();
    }

    @GetMapping("/{id}/orders")
    public ApiResponse<CustomerOrderResponse> getCustomerOrders(@PathVariable Long id) {
        log.info("getCustomerOrders called with: {}", id);
        CustomerOrderResponse customerOrderResponse = customerService.getCustomerOrders(id);
        return ApiResponse.<CustomerOrderResponse>builder()
                .data(customerOrderResponse)
                .status("0")
                .message("success")
                .build();
    }
}
