package com.getir.readingisgoodservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by TCOKARAKAYA on 25.10.2022.
 */
@Getter
@Setter
@Builder
@ToString
public class BookResponse
{
    private String name;
    private String writer;
    private Long stock;
    private BigDecimal price;
}
