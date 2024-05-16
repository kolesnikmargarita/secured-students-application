package by.kolesnik.students.util;

import by.kolesnik.students.config.property.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties jwtProperties;

    // используется при логине
    public String generateToken(String username, Date now, Date expiryDate) {
        final byte[] secret = jwtProperties.getSecret().getBytes();
        final Key key = Keys.hmacShaKeyFor(secret);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    // используется в фильтре
    public Claims parseToken(String token) {
        final byte[] secret = jwtProperties.getSecret().getBytes();
        final Key key = Keys.hmacShaKeyFor(secret);

        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
