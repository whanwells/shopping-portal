package com.example.demo.user;

import com.example.demo.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTests extends BaseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Mock
    private User user;

    @Mock
    private Role role;

    @ParameterizedTest
    @ValueSource(strings = {"/api/users", "/api/users/1"})
    void whenUnauthenticated(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @WithMockUser
    void getAllWhenNotAdmin() throws Exception {
        mockMvc.perform(get("/api/users")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "1")
    void getOne() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("foo@example.com");
        when(user.getRoles()).thenReturn(List.of(role));
        when(role.getId()).thenReturn(1L);
        when(role.getName()).thenReturn("foo");

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("foo@example.com"))
            .andExpect(jsonPath("$.roles.size()").value(1))
            .andExpect(jsonPath("$.roles[0].id").value(1))
            .andExpect(jsonPath("$.roles[0].name").value("foo"));
    }

    @Test
    @WithMockUser(username = "2")
    void getOneButPrincipalMismatch() throws Exception {
        mockMvc.perform(get("/api/users/1")).andExpect(status().isForbidden());
    }
}
