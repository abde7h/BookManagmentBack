package com.book.bookmanagment.controller;

import com.book.bookmanagment.model.Book;
import com.book.bookmanagment.model.User;
import com.book.bookmanagment.service.BookService;
import com.book.bookmanagment.dto.BookDto;
import com.book.bookmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    // Obtener la lista de libros favoritos del usuario autenticado
    @GetMapping("/favorites")
    public List<Book> getFavorites(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return bookService.getUserBooks(user);
    }

    // Añadir un libro a los favoritos del usuario autenticado
    @PostMapping("/favorites")
    public Book addBookToFavorites(Authentication authentication, @RequestBody BookDto bookDto) {
        User user = userService.findByUsername(authentication.getName());
        // Añadimos el libro a los favoritos usando los datos de BookDto
        return bookService.addBookToFavorites(user, bookDto.getTitle(), bookDto.getAuthor());
    }
}
