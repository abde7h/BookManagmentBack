package com.book.bookmanagment.dto;

public class BookDto {
    private String title;
    private String author;

    // Constructor vacío
    public BookDto() {}

    // Constructor con parámetros
    public BookDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
