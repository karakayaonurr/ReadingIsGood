package com.getir.readingisgoodservice.service.impl;

import com.getir.readingisgoodservice.entity.Book;
import com.getir.readingisgoodservice.exception.BookAlreadyExistException;
import com.getir.readingisgoodservice.exception.BookNotFoundException;
import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.response.BookResponse;
import com.getir.readingisgoodservice.model.response.StockResponse;
import com.getir.readingisgoodservice.repository.BookRepository;
import com.getir.readingisgoodservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.getir.readingisgoodservice.exception.ApiErrorType.BOOK_EXIST_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.BOOK_NOT_FOUND_EXCEPTION;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toBook;
import static com.getir.readingisgoodservice.mapper.MapperUtil.toBookResponse;

/**
 * Created by TCOKARAKAYA on 25.10.2022.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService
{
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookResponse createBook(BookRequest request) {
        bookRepository.findOneByName(request.getName()).ifPresent(ex -> {throw new BookAlreadyExistException(BOOK_EXIST_EXCEPTION);});

        Book bookSaved = bookRepository.save(toBook(request));
        log.info("Book saved successfully. Book: {}", bookSaved);
        return toBookResponse(bookSaved);
    }

    @Override
    public BookResponse getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            Book bookPresent = book.get();
            log.info("Book found: {}", bookPresent);
            return toBookResponse(bookPresent);
        } else {
            throw new BookNotFoundException(BOOK_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Optional<Book> getBookByBookId(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public StockResponse getBookStock(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book bookPresent = book.get();
            return StockResponse.builder()
                    .bookId(bookPresent.getId())
                    .bookName(bookPresent.getName())
                    .remainingStock(bookPresent.getStock())
                    .build();
        } else {
            throw new BookNotFoundException(BOOK_NOT_FOUND_EXCEPTION);
        }
    }
}
