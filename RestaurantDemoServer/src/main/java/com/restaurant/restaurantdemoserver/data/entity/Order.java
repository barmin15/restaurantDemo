package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.restaurant.restaurantdemoserver.data.helper.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Table table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Kitchen kitchen;

    @ManyToMany
    @JsonBackReference
    private List<Food> orderedFoods;

    @ManyToMany
    @JsonBackReference
    private List<Drink> orderedDrinks;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


}
