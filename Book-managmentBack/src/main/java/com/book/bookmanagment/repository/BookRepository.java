package com.book.bookmanagment.repository;

import com.book.bookmanagment.model.Book;
import com.book.bookmanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUser(User user);
}
