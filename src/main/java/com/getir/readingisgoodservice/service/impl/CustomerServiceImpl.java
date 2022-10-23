package com.getir.readingisgoodservice.service.impl;

import com.getir.readingisgoodservice.entity.Customer;
import com.getir.readingisgoodservice.model.request.CustomerCreateRequest;
import com.getir.readingisgoodservice.model.response.CustomerCreateResponse;
import com.getir.readingisgoodservice.repository.CustomerRepository;
import com.getir.readingisgoodservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.getir.readingisgoodservice.mapper.MapperUtil.toCustomer;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toCustomerResponse;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerCreateResponse createCustomer(CustomerCreateRequest request)
    {
        Customer customerSaved = customerRepository.save(toCustomer(request));
        log.info("Customer saved successfully. Cusstomer: {}", customerSaved);
        return toCustomerResponse(customerSaved);
    }
}
