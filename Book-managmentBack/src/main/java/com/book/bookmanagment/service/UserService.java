package com.book.bookmanagment.service;

import com.book.bookmanagment.model.User;
import com.book.bookmanagment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        // Verificar si el usuario ya existe
        if(userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Codificar la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password for user " + username + ": " + encodedPassword);

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
