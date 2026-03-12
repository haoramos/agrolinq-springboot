package com.agrolinq.app.services;

public interface JwtService {
    String generateToken(String email, String tipo);
    String extractEmail(String token);
    String extractTipo(String token);
    boolean isTokenValid(String token, String email);
}
