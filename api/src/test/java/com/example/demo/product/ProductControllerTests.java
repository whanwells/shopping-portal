package com.example.demo.product;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTests extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ProductService productService;

    @Mock
    private Category category;

    @Mock
    private Product product;

    @ParameterizedTest
    @ValueSource(strings = {"/api/products", "/api/products/1"})
    void whenUnauthenticated(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void getAll() throws Exception {
        when(category.getName()).thenReturn("foo");
        when(category.getProducts()).thenReturn(List.of(product));
        when(product.getId()).thenReturn(1L);
        when(product.getName()).thenReturn("foo");
        when(product.getReleaseDate()).thenReturn(LocalDate.of(2001, 1, 1));
        when(product.getMsrp()).thenReturn(9.99);
        when(product.isStocked()).thenReturn(true);

        when(categoryService.findAll()).thenReturn(List.of(category));

        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].category").value("foo"))
            .andExpect(jsonPath("$[0].products.size()").value(1))
            .andExpect(jsonPath("$[0].products[0].id").value(1))
            .andExpect(jsonPath("$[0].products[0].name").value("foo"))
            .andExpect(jsonPath("$[0].products[0].releaseDate").value("2001-01-01"))
            .andExpect(jsonPath("$[0].products[0].msrp").value(9.99))
            .andExpect(jsonPath("$[0].products[0].stocked").value(true));
    }

    @Test
    @WithMockUser
    void getAllWithCategory() throws Exception {
        when(categoryService.findByName("foo")).thenReturn(List.of());

        mockMvc.perform(get("/api/products?category=bar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    @WithMockUser
    void getOne() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.of(product));
        when(product.getId()).thenReturn(1L);
        when(product.getName()).thenReturn("foo");
        when(product.getReleaseDate()).thenReturn(LocalDate.of(2001, 1, 1));
        when(product.getMsrp()).thenReturn(9.99);
        when(product.isStocked()).thenReturn(true);

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("foo"))
            .andExpect(jsonPath("$.releaseDate").value("2001-01-01"))
            .andExpect(jsonPath("$.msrp").value(9.99))
            .andExpect(jsonPath("$.stocked").value(true));
    }

    @Test
    @WithMockUser
    void getOneWithInvalidProductId() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/products/1")).andExpect(status().isNotFound());
    }
}
