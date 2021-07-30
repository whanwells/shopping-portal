package com.example.demo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Double msrp;

    @Column(nullable = false)
    private Integer quantity;

    public Product() {}

    public Product(Long id, Category category, String name, LocalDate releaseDate, Double msrp, Integer quantity) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.releaseDate = releaseDate;
        this.msrp = msrp;
        this.quantity = quantity;
    }

    public Product(Category category, String name, LocalDate releaseDate, Double msrp, Integer quantity) {
        this.name = name;
        this.category = category;
        this.releaseDate = releaseDate;
        this.msrp = msrp;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getMsrp() {
        return msrp;
    }

    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
