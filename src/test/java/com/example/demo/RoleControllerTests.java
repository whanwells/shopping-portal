package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
class RoleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService service;

    private static Role createRole() {
        var role = new Role();
        role.setId(1L);
        role.setName("foo");
        return role;
    }

    @Test
    void getsAllRoles() throws Exception {
        when(service.findAll()).thenReturn(List.of(createRole()));

        mockMvc.perform(get("/api/roles"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("foo"))
            .andExpect(jsonPath("$[0].users").doesNotHaveJsonPath());
    }

    @Test
    void getsRoleById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(createRole()));

        mockMvc.perform(get("/api/roles/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("foo"))
            .andExpect(jsonPath("$[0].users").doesNotHaveJsonPath());
    }

    @Test
    void returnsErrorWhenProductIdInvalid() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/roles/1"))
            .andExpect(status().isNotFound());
    }
}
