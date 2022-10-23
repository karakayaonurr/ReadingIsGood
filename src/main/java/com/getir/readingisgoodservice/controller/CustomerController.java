package com.getir.readingisgoodservice.controller;

import com.getir.readingisgoodservice.model.ApiResponse;
import com.getir.readingisgoodservice.model.request.CustomerCreateRequest;
import com.getir.readingisgoodservice.model.response.CustomerCreateResponse;
import com.getir.readingisgoodservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerCreateResponse> createCustomer(@Valid @RequestBody CustomerCreateRequest request) {
        log.info("Create customer called with request: {}", request);
        CustomerCreateResponse response = customerService.createCustomer(request);
        return ApiResponse.<CustomerCreateResponse>builder()
                .data(response)
                .status("OK")
                .message("successful")
                .build();
    }
}
