package com.demo.ejeciciobackend.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;

public class TokenUtils {
    private final static String TOKEN_SECRET = "brr";
    private final static Long TOKEN_VALIDITY = 2592000L;

    public static String createToken(String name, String email) {
        long expirationTime = System.currentTimeMillis() + TOKEN_VALIDITY;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .claim("name", name)
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();

    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            var claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            var email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }

    }

}
