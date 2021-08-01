package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTests extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    private static Role createRole() {
        var role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        return role;
    }

    private static User createUser() {
        var user = new User("foo@example.com", "bar");
        user.setId(1L);
        user.addRole(createRole());
        return user;
    }

    @Test
    void returnsErrorWithInvalidRequest() throws Exception {
        mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void returnsErrorWithExistingEmail() throws Exception {
        when(userService.existsByEmail("foo@example.com"))
            .thenReturn(true);

        mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"foo@example.com\", \"password\": \"bar\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(is("A user with that email already exists")));
    }

    @Test
    void returnsErrorWhenRoleNotFound() throws Exception {
        when(roleService.findByName("ROLE_USER"))
            .thenReturn(Optional.empty());

        mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"foo@example.com\", \"password\": \"bar\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason(is("Unable to assign ROLE_USER")));
    }

    @Test
    void returnsUserWhenSuccessful() throws Exception {
        var user = createUser();

        when(roleService.findByName("ROLE_USER"))
            .thenReturn(Optional.of(createRole()));

        when(userService.save(any(User.class)))
            .thenReturn(user);

        mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"foo@example.com\", \"password\": \"bar\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/users/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("foo@example.com"))
                .andExpect(jsonPath("$.roles.size()").value(1))
                .andExpect(jsonPath("$.roles[0].id").value(1))
                .andExpect(jsonPath("$.roles[0].name").value("ROLE_USER"));
    }
}
