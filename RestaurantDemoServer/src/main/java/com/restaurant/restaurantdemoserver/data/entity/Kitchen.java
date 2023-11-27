package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "kitchens")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Kitchen {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    @OneToMany(mappedBy = "kitchen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Order> orders;

    @OneToOne
    private Restaurant restaurant;

    public void addOrder(Order order){
        order.setKitchen(this);
        if(orders == null) orders = new ArrayList<>();
        orders.add(order);
    }
}
