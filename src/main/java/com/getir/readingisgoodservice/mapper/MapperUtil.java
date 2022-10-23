package com.getir.readingisgoodservice.mapper;

import com.getir.readingisgoodservice.entity.Customer;
import com.getir.readingisgoodservice.model.request.CustomerCreateRequest;
import com.getir.readingisgoodservice.model.response.CustomerCreateResponse;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
public class  MapperUtil {
    public static  Customer toCustomer(CustomerCreateRequest request) {
        return Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .build();
    }
    public static CustomerCreateResponse toCustomerResponse(Customer customer) {
        return CustomerCreateResponse.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .build();
    }
}
