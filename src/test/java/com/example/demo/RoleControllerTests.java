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

@WebMvcTest(RoleController.class)
class RoleControllerTests extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService service;

    private final Role role = new Role();

    @BeforeEach
    void setup() {
        role.setId(1L);
        role.setName("foo");
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/roles", "/api/roles/1"})
    void whenUnauthenticated(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @WithMockUser
    @ValueSource(strings = {"/api/roles", "/api/roles/1"})
    void whenUnauthorized(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(role));

        mockMvc.perform(get("/api/roles"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("foo"))
            .andExpect(jsonPath("$[0].users").doesNotHaveJsonPath());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(role));

        mockMvc.perform(get("/api/roles/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("foo"))
            .andExpect(jsonPath("$[0].users").doesNotHaveJsonPath());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getByIdWithInvalidId() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/roles/1")).andExpect(status().isNotFound());
    }
}
