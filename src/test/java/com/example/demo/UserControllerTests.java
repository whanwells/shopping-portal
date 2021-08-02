package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
class UserControllerTests extends BaseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService service;

    private final User user = new User();

    @BeforeEach
    void setup() {
        var role = new Role();
        role.setId(2L);
        role.setName("bar");

        user.setId(1L);
        user.setEmail("foo@example.com");
        user.addRole(role);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/users", "/api/users/1"})
    void whenUnauthenticated(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @WithMockUser
    @ValueSource(strings = {"/api/users", "/api/users/1"})
    void whenUnauthorized(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].email").value("foo@example.com"))
            .andExpect(jsonPath("$[0].roles.size()").value(1))
            .andExpect(jsonPath("$[0].roles[0].id").value(2))
            .andExpect(jsonPath("$[0].roles[0].name").value("bar"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("foo@example.com"))
            .andExpect(jsonPath("$.roles.size()").value(1))
            .andExpect(jsonPath("$.roles[0].id").value(2))
            .andExpect(jsonPath("$.roles[0].name").value("bar"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getByIdWithInvalidId() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/users/1")).andExpect(status().isNotFound());
    }
}
