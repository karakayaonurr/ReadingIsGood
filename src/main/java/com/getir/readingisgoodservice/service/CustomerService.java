package com.getir.readingisgoodservice.service;

import com.getir.readingisgoodservice.model.request.CustomerCreateRequest;
import com.getir.readingisgoodservice.model.response.CustomerCreateResponse;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
public interface  CustomerService  {
    CustomerCreateResponse createCustomer(CustomerCreateRequest request);
}
