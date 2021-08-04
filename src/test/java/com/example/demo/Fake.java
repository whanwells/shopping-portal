package com.example.demo;

import com.example.demo.cart.Item;

import java.time.LocalDate;

public class Fake {

    private Fake() {}

    public static User user() {
        return new User("foo@exampe.com", "bar");
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
