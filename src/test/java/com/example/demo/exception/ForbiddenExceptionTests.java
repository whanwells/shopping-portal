package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.security.Principal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForbiddenExceptionTests {

    @Mock
    private Principal principal;

    @Test
    void constructor() {
        var exception = new ForbiddenException();
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void throwIfPrincipalMismatchWithMatchingPrincipal() {
        when(principal.getName()).thenReturn("foo");

        assertThatNoException()
            .isThrownBy(() -> ForbiddenException.throwIfPrincipalMismatch(principal, "foo"));
    }

    @Test
    void throwIfPrincipalMismatchWithMismatchedPrincipal() {
        when(principal.getName()).thenReturn("foo");

        assertThatExceptionOfType(ForbiddenException.class)
            .isThrownBy(() -> ForbiddenException.throwIfPrincipalMismatch(principal, "bar"));
    }
}
