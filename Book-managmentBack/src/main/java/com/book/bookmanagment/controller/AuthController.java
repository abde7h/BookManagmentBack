package com.book.bookmanagment.controller;

import com.book.bookmanagment.security.JWTUtil;
import com.book.bookmanagment.service.UserService;
import com.book.bookmanagment.dto.UserDto;
import com.book.bookmanagment.dto.AuthRequest;
import com.book.bookmanagment.dto.AuthResponse; // Asegúrate de importar AuthResponse
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        try {
            userService.registerUser(userDto.getUsername(), userDto.getPassword());
            System.out.println("User " + userDto.getUsername() + " registered successfully.");
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            System.err.println("Registration failed for user " + userDto.getUsername() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) { // Cambiado a AuthRequest
        try {
            System.out.println("Attempting to authenticate user: " + authRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.generateToken(authRequest.getUsername());

            System.out.println("User " + authRequest.getUsername() + " authenticated successfully. Token: " + token);

            return ResponseEntity.ok(new AuthResponse(token)); // Retorna AuthResponse
        } catch (BadCredentialsException e) {
            System.err.println("Authentication failed for user: " + authRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        } catch (Exception e) {
            System.err.println("An error occurred during login for user " + authRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }
}
