package com.getir.readingisgoodservice.mapper;

import com.getir.readingisgoodservice.entity.Book;
import com.getir.readingisgoodservice.entity.Customer;
import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.request.CustomerRequest;
import com.getir.readingisgoodservice.model.response.BookResponse;
import com.getir.readingisgoodservice.model.response.CustomerResponse;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
public class MapperUtil
{
    public static Customer toCustomer(CustomerRequest request)
    {
        return Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .build();
    }

    public static CustomerResponse toCustomerResponse(Customer request)
    {
        return CustomerResponse.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .build();
    }

    public static Book toBook(BookRequest request)
    {
        return Book.builder()
                .name(request.getName())
                .writer(request.getWriter())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();
    }

    public static BookResponse toBookResponse(Book request)
    {
        return BookResponse.builder()
                .writer(request.getWriter())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();
    }
}
