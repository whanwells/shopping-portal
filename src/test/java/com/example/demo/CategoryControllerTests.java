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

@WebMvcTest(CategoryController.class)
class CategoryControllerTests extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService service;

    private static Category createCategory() {
        var category = new Category();
        category.setId(1L);
        category.setName("foo");
        return category;
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/categories", "/api/categories/1"})
    void blocksUnauthenticatedUsers(String path) throws Exception {
        mockMvc.perform(get(path))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void getsAllCategories() throws Exception {
        when(service.findAll()).thenReturn(List.of(createCategory()));

        mockMvc.perform(get("/api/categories"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("foo"))
            .andExpect(jsonPath("$[0].products").doesNotHaveJsonPath());
    }

    @Test
    @WithMockUser
    void getsCategoryById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(createCategory()));

        mockMvc.perform(get("/api/categories/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("foo"))
            .andExpect(jsonPath("$[0].products").doesNotHaveJsonPath());
    }

    @Test
    @WithMockUser
    void returnsErrorWithInvalidCategoryId() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/categories/1"))
            .andExpect(status().isNotFound());
    }
}
