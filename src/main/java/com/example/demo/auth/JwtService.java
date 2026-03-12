package com.example.demo.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.util.Date;
import javax.crypto.SecretKey;


@Service
public class JwtService {
    
    private static final String SECRE_STRING = "mysecretkeymysecretkeymysecretkeymysecretkey";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRE_STRING.getBytes());

    public String generateToken(String email) {
    return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SECRET_KEY)
            .compact();
}

public String extrairEmail(String token) {
    return Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
}

public boolean validarToken(String token) {
    try {
        extrairEmail(token);
        return true;
    } catch (Exception e) {
        return false;
    }
}

    

}
