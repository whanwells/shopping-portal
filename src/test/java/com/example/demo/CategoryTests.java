package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class CategoryTests {

    @Test
    void constructsWithNoArgs() {
        var category = new Category();

        assertSoftly(softly -> {
            softly.assertThat(category.getId()).isNull();
            softly.assertThat(category.getName()).isNull();
        });
    }

    @Test
    void constructsWithAllProperties() {
        var category = new Category(1L, "foo");

        assertSoftly(softly -> {
            softly.assertThat(category.getId()).isEqualTo(1);
            softly.assertThat(category.getName()).isEqualTo("foo");
        });
    }

    @Test
    void constructsWithName() {
        var category = new Category("foo");

        assertSoftly(softly -> {
            softly.assertThat(category.getId()).isNull();
            softly.assertThat(category.getName()).isEqualTo("foo");
        });
    }

    @Test
    void setsId() {
        var category = new Category();
        category.setId(1L);
        assertThat(category.getId()).isEqualTo(1L);
    }

    @Test
    void setsName() {
        var category = new Category();
        category.setName("foo");
        assertThat(category.getName()).isEqualTo("foo");
    }
}
