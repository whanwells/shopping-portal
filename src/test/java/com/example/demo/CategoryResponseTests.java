package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryResponseTests {

    @Mock
    private Category category;

    @BeforeEach
    void setup() {
        when(category.getId()).thenReturn(1L);
        when(category.getName()).thenReturn("foo");
    }

    @Test
    void fromCategory() {
        var response = CategoryResponse.from(category);

        assertSoftly(s -> {
            s.assertThat(response.getId()).isEqualTo(1L);
            s.assertThat(response.getName()).isEqualTo("foo");
        });
    }

    @Test
    void fromCategoryList() {
        var response = CategoryResponse.from(List.of(category));

        assertSoftly(s -> {
            s.assertThat(response).hasSize(1);
            s.assertThat(response.get(0).getId()).isEqualTo(1L);
            s.assertThat(response.get(0).getName()).isEqualTo("foo");
        });
    }
}
