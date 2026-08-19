package com.nutribox.NutriBox.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // En produccion esto deberia venir de una variable de entorno, no hardcodeado
    private static final String SECRET = "nutribox-clave-secreta-super-larga-para-firmar-tokens-jwt-2026-ica";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXPIRACION_MS = 1000 * 60 * 60 * 24; // 24 horas

    public String generarToken(String email, String rol, Long usuarioId) {
        return Jwts.builder()
                .subject(email)
                .claim("rol", rol)
                .claim("usuarioId", usuarioId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRACION_MS))
                .signWith(key)
                .compact();
    }

    public String extraerEmail(String token) {
        return extraerClaim(token, claims -> claims.getSubject());
    }

    public String extraerRol(String token) {
        return extraerClaim(token, claims -> claims.get("rol", String.class));
    }

    public Long extraerUsuarioId(String token) {
        return extraerClaim(token, claims -> claims.get("usuarioId", Long.class));
    }

    private <T> T extraerClaim(String token, Function<io.jsonwebtoken.Claims, T> resolver) {
        var claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return resolver.apply(claims);
    }

    public boolean esTokenValido(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}