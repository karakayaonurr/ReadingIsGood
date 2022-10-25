package com.getir.readingisgoodservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    private Long customerId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price can not be less than 0.")
    private BigDecimal totalPrice;

    @NotEmpty(message = "Order must have more than one book")
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Book> bookList;

    private Long totalBookCount;
}
