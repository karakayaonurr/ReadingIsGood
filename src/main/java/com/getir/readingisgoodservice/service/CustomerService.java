package com.getir.readingisgoodservice.service;

import com.getir.readingisgoodservice.model.request.CustomerRequest;
import com.getir.readingisgoodservice.model.response.CustomerResponse;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
public interface CustomerService
{
    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerByName(String request);
}
