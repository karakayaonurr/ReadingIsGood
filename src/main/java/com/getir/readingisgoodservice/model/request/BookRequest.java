package com.getir.readingisgoodservice.model.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by TCOKARAKAYA on 25.10.2022.
 */
@Getter
@ToString
public class BookRequest {
    @NotEmpty(message = "Name can not be empty.")
    private String name;

    @NotEmpty(message = "Writer can not be empty.")
    private String writer;

    @NotNull(message = "Stock can not be null.")
    private Long stock;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price can not be less than 0.")
    private BigDecimal price;
}
