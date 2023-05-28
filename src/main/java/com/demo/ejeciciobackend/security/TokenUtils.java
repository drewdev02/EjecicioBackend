package com.demo.ejeciciobackend.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
@Slf4j
public class TokenUtils {
    private final static String TOKEN_SECRET = "brr";
    private final static Long TOKEN_VALIDITY = 2592000L;
    public static String createToken(String name, String email) {
        var expirationTime = System.currentTimeMillis() + TOKEN_VALIDITY;
        var expirationDate = new Date(expirationTime);
        log.info("Creating token for user: {}", email);
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
            log.info("Authenticating user with token: {}", token);
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            log.error("Failed to authenticate user with token: {}", token, e);
            return null;
        }
    }

}
