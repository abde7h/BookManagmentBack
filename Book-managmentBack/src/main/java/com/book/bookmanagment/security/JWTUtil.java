package com.book.bookmanagment.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationTime}")
    private long expirationTime;

    // Generar un token JWT con el nombre de usuario
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // El "subject" es el nombre de usuario
                .setIssuedAt(new Date())  // Fecha de emisión del token
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // Fecha de expiración
                .signWith(SignatureAlgorithm.HS512, secret)  // Firmar el token con la clave secreta
                .compact();
    }

    // Obtener el nombre de usuario desde el token JWT
    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)  // Verificar la firma con la clave secreta
                    .parseClaimsJws(token)  // Parsear el token JWT
                    .getBody()
                    .getSubject();  // Obtener el "subject", que es el nombre de usuario
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            System.err.println("Error parsing token: " + e.getMessage());
            return null;
        }
    }

    // Validar si el token es válido
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username != null &&
                username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }

    // Verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Obtener la fecha de expiración del token
    public Date getExpirationDateFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (Exception e) {
            System.err.println("Error getting expiration date from token: " + e.getMessage());
            return null;
        }
    }
}
