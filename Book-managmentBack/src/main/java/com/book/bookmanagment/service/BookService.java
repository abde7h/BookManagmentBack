package com.book.bookmanagment.service;

import com.book.bookmanagment.model.Book;
import com.book.bookmanagment.model.User;
import com.book.bookmanagment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getUserBooks(User user) {
        return bookRepository.findByUser(user);
    }

    public Book addBookToFavorites(User user, String title, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setUser(user);
        return bookRepository.save(book);
    }
}

