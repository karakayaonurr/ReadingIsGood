package com.getir.readingisgoodservice.service.impl;

import com.getir.readingisgoodservice.entity.Customer;
import com.getir.readingisgoodservice.entity.Order;
import com.getir.readingisgoodservice.model.request.CustomerRequest;
import com.getir.readingisgoodservice.model.response.CustomerOrderResponse;
import com.getir.readingisgoodservice.model.response.CustomerResponse;
import com.getir.readingisgoodservice.repository.CustomerRepository;
import com.getir.readingisgoodservice.repository.OrderRepository;
import com.getir.readingisgoodservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.getir.readingisgoodservice.mapper.MapperUtil.toCustomer;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toCustomerResponse;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toOrderDetailResponses;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customerSaved = customerRepository.save(toCustomer(request));
        log.info("Customer saved successfully. Cusstomer: {}", customerSaved);
        return toCustomerResponse(customerSaved);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            Customer customerPresent = customer.get();
            log.info("Customer found: {}", customerPresent);
            return toCustomerResponse(customerPresent);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public CustomerOrderResponse getCustomerOrders(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            Customer customerPresent = customer.get();
            log.info("Customer found: {}", customerPresent);

            List<Order> orders = orderRepository.getAllByCustomerId(customerPresent.getId());

            return CustomerOrderResponse.builder()
                    .customer(toCustomerResponse(customerPresent))
                    .orders(toOrderDetailResponses(orders))
                    .build();
        } else {
            throw new RuntimeException();
        }
    }
}
