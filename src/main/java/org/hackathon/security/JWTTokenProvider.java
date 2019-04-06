package org.hackathon.security;

import io.jsonwebtoken.*;
import org.hackathon.config.SecurityProperties;
import org.hackathon.entity.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;

public class JWTTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenProvider.class);

    private SecurityProperties properties;

    public JWTTokenProvider(SecurityProperties properties) {
        this.properties = properties;
    }

    public String generateToken(Authentication authentication) {
        Principal userPrincipal = (Principal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + properties.getExpiration());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("role", userPrincipal.getRole());
        return Jwts.builder()
                .setClaims(hashMap)
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuer(userPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}