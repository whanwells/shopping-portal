package com.example.demo;

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

    private static User createUser() {
        var user = new User();
        user.setId(1L);
        user.setEmail("foo@example.com");
        user.addRole(createRole());
        return user;
    }

    private static Role createRole() {
        var role = new Role();
        role.setId(2L);
        role.setName("bar");
        return role;
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/users", "/api/users/1"})
    void blocksUnauthenticatedUsers(String path) throws Exception {
        mockMvc.perform(get(path))
            .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @WithMockUser
    @ValueSource(strings = {"/api/users", "/api/users/1"})
    void blocksUnauthorizedUsers(String path) throws Exception {
        mockMvc.perform(get(path))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getsAllUsers() throws Exception {
        when(service.findAll()).thenReturn(List.of(createUser()));

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].email").value("foo@example.com"))
            .andExpect(jsonPath("$[0].password").doesNotHaveJsonPath())
            .andExpect(jsonPath("$[0].roles.size()").value(1))
            .andExpect(jsonPath("$[0].roles[0].id").value(2))
            .andExpect(jsonPath("$[0].roles[0].name").value("bar"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getsUserById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(createUser()));

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("foo@example.com"))
            .andExpect(jsonPath("$.password").doesNotHaveJsonPath())
            .andExpect(jsonPath("$.roles.size()").value(1))
            .andExpect(jsonPath("$.roles[0].id").value(2))
            .andExpect(jsonPath("$.roles[0].name").value("bar"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void returnsErrorWithInvalidUserId() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isNotFound());
    }
}
