package com.getir.readingisgoodservice.service;

import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.response.BookResponse;

/**
 * Created by TCOKARAKAYA on 25.10.2022.
 */
public interface BookService {
    BookResponse createBook(BookRequest request);
    BookResponse getBookById(Long id);
}
