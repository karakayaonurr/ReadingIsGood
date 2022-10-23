package com.getir.readingisgoodservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Entity
@Table(name = "book")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    @NotEmpty(message = "Name can not be empty.")
    private String name;

    @NotEmpty(message = "Writer can not be empty.")
    private String writer;

    @NotNull(message = "Stock can not be null.")
    private Long stock;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price can not be less than 0.")
    private BigDecimal price;
}
