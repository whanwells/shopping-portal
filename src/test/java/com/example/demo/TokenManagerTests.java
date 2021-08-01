package com.example.demo;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenManagerTests {

    @Mock
    private JWTCreator.Builder builder;

    @Mock
    private JWTVerifier verifier;

    @Mock
    private Algorithm algorithm;

    @Mock
    private DecodedJWT jwt;

    @InjectMocks
    private TokenManager tokenManager;

    @Mock
    private CustomUser customUser;

    @Test
    void createsTokens() {
        when(customUser.getId()).thenReturn(1L);
        when(builder.withSubject("1")).thenReturn(builder);
        when(builder.withExpiresAt(any(Date.class))).thenReturn(builder);
        when(builder.withClaim(any(String.class), anyList())).thenReturn(builder);
        when(builder.sign(algorithm)).thenReturn("bar");
        assertThat(tokenManager.create(customUser)).isEqualTo("bar");
    }

    @Test
    void returnsEmptyWhenExtractingFromNullAuthorizationHeader() {
        assertThat(tokenManager.extract(null)).isEmpty();
    }

    @Test
    void returnsEmptyWhenExtractingFromNonBearerAuthorizationHeader() {
        assertThat(tokenManager.extract("")).isEmpty();
    }

    @Test
    void returnsEmptyWhenExtractingFromAuthorizationHeaderWithoutBearerToken() {
        assertThat(tokenManager.extract("Bearer ")).isEmpty();
    }

    @Test
    void returnsTokenWhenExtractingFromAuthorizationHeaderWithBearerToken() {
        assertThat(tokenManager.extract("Bearer foo")).contains("foo");
    }

    @Test
    void decodesTokens() {
        when(verifier.verify("foo")).thenReturn(jwt);
        assertThat(tokenManager.decode("foo")).isEqualTo(jwt);
    }
}
