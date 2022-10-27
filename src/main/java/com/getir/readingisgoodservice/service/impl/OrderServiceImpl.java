package com.getir.readingisgoodservice.service.impl;

import com.getir.readingisgoodservice.entity.Book;
import com.getir.readingisgoodservice.entity.Order;
import com.getir.readingisgoodservice.exception.ApiErrorType;
import com.getir.readingisgoodservice.exception.BookNotFoundException;
import com.getir.readingisgoodservice.exception.OrderNotFoundException;
import com.getir.readingisgoodservice.model.request.OrderCreateRequest;
import com.getir.readingisgoodservice.model.response.CustomerResponse;
import com.getir.readingisgoodservice.model.response.OrderCreateResponse;
import com.getir.readingisgoodservice.model.response.OrderResponse;
import com.getir.readingisgoodservice.repository.OrderRepository;
import com.getir.readingisgoodservice.service.BookService;
import com.getir.readingisgoodservice.service.CustomerService;
import com.getir.readingisgoodservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.getir.readingisgoodservice.exception.ApiErrorType.BOOK_NOT_FOUND_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.ORDER_NOT_FOUND_EXCEPTION;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toOrder;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toOrderCreateResponse;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toOrderResponses;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final CustomerService customerService;
    @Override
    @Transactional
    public OrderCreateResponse createOrder(OrderCreateRequest request) {
        List<Book> books = new ArrayList<>();
        request.getBookOrderRequests().forEach(bookOrder -> {
            Optional<Book> book = bookService.getBookByBookId(bookOrder.getBookId());
            if (book.isPresent()) {
                Book bookPresent = book.get();
                books.add(bookPresent);
                bookPresent.setStock(bookPresent.getStock() - bookOrder.getOrderCount());
            } else {
                throw new BookNotFoundException(BOOK_NOT_FOUND_EXCEPTION);
            }
        });
        Order orderSaved = orderRepository.save(toOrder(request, books));
        log.info("Customer saved successfully. Customer: {}", orderSaved);

        List<OrderResponse> orderResponses = toOrderResponses(request.getBookOrderRequests());
        CustomerResponse customerResponse = customerService.getCustomerById(request.getCustomerId());
        return toOrderCreateResponse(orderResponses, customerResponse);
    }

    @Override
    public List<Order> getCustomerOrders(Long customerId) {

        List<Order> orders = orderRepository.getAllByCustomerId(customerId);

        if(orders.isEmpty()) {
            throw new OrderNotFoundException(ORDER_NOT_FOUND_EXCEPTION);
        }

        return orders;
    }
}
