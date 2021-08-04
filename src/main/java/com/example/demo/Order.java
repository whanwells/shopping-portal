package com.example.demo;

import com.example.demo.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_t")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<OrderLine> lines = new ArrayList<>();

    public static Order from(User user) {
        var order = new Order();
        user.addOrder(order);
        return order;
    }

    public void addLine(OrderLine line) {
        lines.add(line);
        line.setOrder(this);
    }

    public void removeLine(OrderLine line) {
        lines.remove(line);
        line.setOrder(null);
    }

    public boolean isOpen() {
        return date != null;
    }
}
