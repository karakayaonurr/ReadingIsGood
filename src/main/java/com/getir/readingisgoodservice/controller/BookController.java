package com.getir.readingisgoodservice.controller;

import com.getir.readingisgoodservice.model.ApiResponse;
import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.response.BookResponse;
import com.getir.readingisgoodservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by TCOKARAKAYA on 25.10.2022.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/book")
public class BookController
{
    private final BookService bookService;

    @PostMapping("/create")
    public ApiResponse<BookResponse> createBook(@Valid @RequestBody BookRequest request)
    {
        log.info("Create book called with request: {}", request);
        BookResponse response = bookService.createBook(request);
        return ApiResponse.<BookResponse>builder()
                .data(response)
                .status("OK")
                .message("successful")
                .build();
    }

    @GetMapping("/getByName/{name}")
    public ApiResponse<BookResponse> getBookByName(@PathVariable String request)
    {
        log.info("getBookByName called with: {}", request);
        BookResponse bookResponse = bookService.getBookByName(request);
        return ApiResponse.<BookResponse>builder()
                .data(bookResponse)
                .status("0")
                .message("success")
                .build();
    }
}
