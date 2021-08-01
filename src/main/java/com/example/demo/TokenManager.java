package com.example.demo;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TokenManager {

    private static final String PREFIX = "Bearer ";

    private final JWTCreator.Builder builder;
    private final JWTVerifier verifier;
    private final Algorithm algorithm;

    public TokenManager(JWTCreator.Builder builder, JWTVerifier verifier, Algorithm algorithm) {
        this.builder = builder;
        this.verifier = verifier;
        this.algorithm = algorithm;
    }

    public String create(CustomUser user) {
        return builder
            .withSubject(String.valueOf(user.getId()))
            .withExpiresAt(Date.from(OffsetDateTime.now().plusHours(1).toInstant()))
            .withClaim("roles", mapAuthorities(user.getAuthorities()))
            .sign(algorithm);
    }

    private static List<String> mapAuthorities(Collection<GrantedAuthority> authorities) {
        return authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    }

    public Optional<String> extract(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(PREFIX)) {
            return Optional.empty();
        }
        var token = authorizationHeader.substring(PREFIX.length());
        return token.isBlank() ? Optional.empty() : Optional.of(token);
    }

    public DecodedJWT decode(String token) throws JWTVerificationException {
        return verifier.verify(token);
    }
}
