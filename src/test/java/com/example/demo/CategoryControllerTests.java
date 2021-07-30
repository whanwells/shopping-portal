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

@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService service;

    @Test
    void getsAllCategories() throws Exception {
        when(service.findAll()).thenReturn(List.of(new Category(1L, "foo")));

        mockMvc.perform(get("/api/categories"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("foo"));
    }

    @Test
    void getsCategoryById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(new Category(1L, "foo")));

        mockMvc.perform(get("/api/categories/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("foo"));
    }

    @Test
    void returnsErrorWhenCategoryIdInvalid() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/categories/1"))
            .andExpect(status().isNotFound());
    }
}