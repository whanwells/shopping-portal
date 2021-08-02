package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationResponseTests {

    @Mock
    private User user;

    @Test
    void from() {
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("foo@example.com");

        var response = RegistrationResponse.from(user);

        assertSoftly(s -> {
            s.assertThat(response.getId()).isEqualTo(1);
            s.assertThat(response.getEmail()).isEqualTo("foo@example.com");
        });
    }
}
