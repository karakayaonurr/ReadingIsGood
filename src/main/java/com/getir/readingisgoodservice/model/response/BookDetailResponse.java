package com.getir.readingisgoodservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BookDetailResponse {
    private String bookName;
}
