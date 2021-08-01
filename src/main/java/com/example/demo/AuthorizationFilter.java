package com.example.demo;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final TokenManager tokenManager;

    public AuthorizationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        tokenManager.extract(request.getHeader(HttpHeaders.AUTHORIZATION))
            .ifPresent(this::processToken);

        chain.doFilter(request, response);
    }

    private void processToken(String token) {
        SecurityContextHolder.getContext().setAuthentication(createAuthentication(token));
    }

    private Authentication createAuthentication(String token) {
        var jwt = tokenManager.decode(token);
        return new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, mapRoles(jwt.getClaim("roles")));
    }

    private List<? extends GrantedAuthority> mapRoles(Claim claim) {
        return Arrays.stream(claim.asArray(String.class))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }
}
