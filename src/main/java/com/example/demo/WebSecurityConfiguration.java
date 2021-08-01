package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    private final TokenManager tokenManager;

    private final ObjectMapper objectMapper;

    public WebSecurityConfiguration(CustomUserDetailsService customUserDetailsService, TokenManager tokenManager, ObjectMapper objectMapper) {
        this.customUserDetailsService = customUserDetailsService;
        this.tokenManager = tokenManager;
        this.objectMapper = objectMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/login", "/api/register").permitAll()
                .anyRequest().authenticated()
            .and()
            .addFilter(new AuthenticationFilter(authenticationManager(), tokenManager, objectMapper))
            .addFilter(new AuthorizationFilter(authenticationManager(), tokenManager))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
