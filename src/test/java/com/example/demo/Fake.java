package com.example.demo;

import com.example.demo.cart.Item;
import com.example.demo.product.Category;
import com.example.demo.product.Product;
import com.example.demo.user.Role;
import com.example.demo.user.User;

import java.time.LocalDate;

public class Fake {

    private Fake() {}

    public static Role role() {
        var role = new Role();
        role.setName("foo");
        return role;
    }

    public static User user() {
        var user = new User();
        user.setEmail("foo@example.com");
        user.setPassword("foo");
        return user;
    }

    public static Category category() {
        var category = new Category();
        category.setName("foo");
        return category;
    }

    public static Product product(Category category) {
        var product = new Product();
        product.setName("foo");
        product.setCategory(category);
        product.setReleaseDate(LocalDate.of(2001, 1, 1));
        product.setMsrp(9.99);
        product.setQuantity(9);
        return product;
    }

    public static Item item(User user, Product product) {
        var item = new Item();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(9);
        return item;
    }
}
