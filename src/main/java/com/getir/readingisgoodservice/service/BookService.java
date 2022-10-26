package com.getir.readingisgoodservice.service;

import com.getir.readingisgoodservice.entity.Book;
import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.response.BookResponse;
import com.getir.readingisgoodservice.model.response.StockResponse;

import java.util.Optional;

/**
 * Created by TCOKARAKAYA on 25.10.2022.
 */
public interface BookService {
    BookResponse createBook(BookRequest request);
    BookResponse getBookById(Long id);
    Optional<Book> getBookByBookId(Long id);

    StockResponse getBookStock(Long id);
}
