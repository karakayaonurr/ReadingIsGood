package com.getir.readingisgoodservice.mapper;

import com.getir.readingisgoodservice.entity.Book;
import com.getir.readingisgoodservice.entity.Customer;
import com.getir.readingisgoodservice.entity.Order;
import com.getir.readingisgoodservice.model.OrderTotalCount;
import com.getir.readingisgoodservice.model.request.BookOrderRequest;
import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.request.CustomerRequest;
import com.getir.readingisgoodservice.model.request.OrderCreateRequest;
import com.getir.readingisgoodservice.model.response.BookDetailResponse;
import com.getir.readingisgoodservice.model.response.BookResponse;
import com.getir.readingisgoodservice.model.response.CustomerResponse;
import com.getir.readingisgoodservice.model.response.OrderCreateResponse;
import com.getir.readingisgoodservice.model.response.OrderDetailResponse;
import com.getir.readingisgoodservice.model.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.getir.readingisgoodservice.entity.Order.calculate;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
public class MapperUtil
{
    public static Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .build();
    }

    public static CustomerResponse toCustomerResponse(Customer request) {
        return CustomerResponse.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .build();
    }

    public static Book toBook(BookRequest request) {
        return Book.builder()
                .name(request.getName())
                .writer(request.getWriter())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();
    }

    public static BookResponse toBookResponse(Book request) {
        return BookResponse.builder()
                .name(request.getName())
                .writer(request.getWriter())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();
    }

    public static Order toOrder(OrderCreateRequest request, List<Book> books) {
        OrderTotalCount orderTotalCount = calculate(books, request.getBookOrderRequests());
        return Order.builder()
                .customerId(request.getCustomerId())
                .bookList(books)
                .totalPrice(orderTotalCount.getTotalAmount())
                .totalBookCount(orderTotalCount.getTotalBook())
                .build();
    }

    public static OrderCreateResponse toOrderCreateResponse(List<OrderResponse> orderResponse, CustomerResponse customerResponse) {
        return OrderCreateResponse.builder()
                .customer(customerResponse)
                .orders(orderResponse)
                .build();
    }

    public static OrderResponse toOrderResponse(BookOrderRequest bookOrderRequest) {
        return OrderResponse.builder()
                .bookId(bookOrderRequest.getBookId())
                .orderCount(bookOrderRequest.getOrderCount())
                .build();
    }

    public static List<OrderResponse> toOrderResponses(List<BookOrderRequest> bookOrderRequests) {
        return bookOrderRequests.stream().map(MapperUtil::toOrderResponse).collect(Collectors.toList());
    }

    public static OrderDetailResponse toOrderDetailResponse(Order order) {
        List<BookDetailResponse> bookDetailResponseList = new ArrayList<>();
        order.getBookList().forEach(book -> {
            bookDetailResponseList.add(toBookDetailResponse(book));
        });
        return OrderDetailResponse.builder()
                .books(bookDetailResponseList)
                .totalBook(order.getTotalBookCount())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static List<OrderDetailResponse> toOrderDetailResponses(List<Order> orders) {
        return orders.stream().map(MapperUtil::toOrderDetailResponse).collect(Collectors.toList());
    }

    private static BookDetailResponse toBookDetailResponse(Book book) {
        return BookDetailResponse.builder()
                .bookName(book.getName())
                .build();
    }
}
