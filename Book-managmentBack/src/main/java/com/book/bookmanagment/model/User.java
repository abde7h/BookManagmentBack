package com.book.bookmanagment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Asegúrate de que la tabla se llama 'users' o ajusta según tu configuración
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Getters y setters

    public Long getId() {
        return id;
    }

    // Otros getters y setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
