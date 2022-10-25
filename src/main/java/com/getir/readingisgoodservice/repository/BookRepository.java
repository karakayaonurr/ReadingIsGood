package com.getir.readingisgoodservice.repository;

import com.getir.readingisgoodservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
