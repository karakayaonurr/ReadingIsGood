package com.getir.readingisgoodservice.service.impl;

import com.getir.readingisgoodservice.entity.Book;
import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.response.BookResponse;
import com.getir.readingisgoodservice.repository.BookRepository;
import com.getir.readingisgoodservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public BookResponse createBook(BookRequest request)
    {
        Book bookSaved = bookRepository.save(toBook(request));
        log.info("Customer saved successfully. Cusstomer: {}", bookSaved);
        return toBookResponse(bookSaved);
    }

    @Override
    public BookResponse getBookByName(String request)
    {
        Optional<Book> book = Optional.ofNullable(bookRepository.findByName(request));

        if (book.isPresent())
        {
            Book bookPresent = book.get();

            log.info("Books found: {}", bookPresent);

            return toBookResponse(bookPresent);
        }
        else
        {
            throw new RuntimeException();
        }
    }
}
