package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    private static Category createCategory() {
        var category = new Category();
        category.setId(1L);
        category.setName("foo");
        return category;
    }

    private static Product createProduct() {
        var product = new Product();
        product.setId(1L);
        product.setName("bar");
        product.setReleaseDate(LocalDate.of(2000, 1, 1));
        product.setMsrp(9.99);
        product.setQuantity(5);
        product.setCategory(createCategory());
        return product;
    }

    @Test
    void getAllProducts() throws Exception {
        when(service.findAll()).thenReturn(List.of(createProduct()));

        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].category").value("foo"))
            .andExpect(jsonPath("$[0].name").value("bar"))
            .andExpect(jsonPath("$[0].releaseDate").value("2000-01-01"))
            .andExpect(jsonPath("$[0].msrp").value(9.99))
            .andExpect(jsonPath("$[0].quantity").doesNotHaveJsonPath())
            .andExpect(jsonPath("$[0].stocked").value(true));
    }

    @Test
    void getAllProductsByName() throws Exception {
        when(service.findByCategoryName("bar")).thenReturn(List.of());

        mockMvc.perform(get("/api/products?category=baz"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    void getProductById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(createProduct()));

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.category").value("foo"))
            .andExpect(jsonPath("$.name").value("bar"))
            .andExpect(jsonPath("$.releaseDate").value("2000-01-01"))
            .andExpect(jsonPath("$.msrp").value(9.99))
            .andExpect(jsonPath("$.quantity").doesNotHaveJsonPath())
            .andExpect(jsonPath("$.stocked").value(true));
    }

    @Test
    void returnsErrorWhenProductIdInvalid() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isNotFound());
    }
}
