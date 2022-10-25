package com.getir.readingisgoodservice.entity;

import com.getir.readingisgoodservice.model.OrderTotalCount;
import com.getir.readingisgoodservice.model.request.BookOrderRequest;
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

import static java.math.BigDecimal.ZERO;

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

    public static OrderTotalCount calculate(List<Book> books, List<BookOrderRequest> bookOrderRequests) {
        BigDecimal totalAmount = ZERO;
        long totalBooks = 0;
        for (BookOrderRequest bookOrderRequest : bookOrderRequests) {
            BigDecimal bookPrice = books.stream()
                    .filter(b -> b.getId().equals(bookOrderRequest.getBookId()))
                    .map(Book::getPrice)
                    .findFirst()
                    .orElse(ZERO);
            totalAmount = totalAmount.add(bookPrice.multiply(BigDecimal.valueOf(bookOrderRequest.getOrderCount())));
            long bookSize = bookOrderRequests.stream()
                    .filter(bor -> bor.getBookId().equals(bookOrderRequest.getBookId()))
                    .map(BookOrderRequest::getOrderCount)
                    .findFirst()
                    .orElse(0L);
            totalBooks = totalBooks + bookSize;
        }
        return OrderTotalCount.builder()
                .totalAmount(totalAmount)
                .totalBook(totalBooks)
                .build();
    }

}
