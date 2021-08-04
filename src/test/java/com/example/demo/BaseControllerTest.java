package com.example.demo;

import org.springframework.boot.test.mock.mockito.MockBean;

// Controller tests should extend this class, which will inject all necessary
// mock beans so the application context will load.
public abstract class BaseControllerTest {

    @MockBean
    protected CustomUserDetailsService customUserDetailsService;

    @MockBean
    protected TokenManager tokenManager;
}
