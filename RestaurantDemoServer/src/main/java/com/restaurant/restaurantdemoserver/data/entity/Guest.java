package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "guests")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Guest {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<Food> orderedFoods;

    @OneToMany
    private Set<Food> orderedDrinks;

    private Float totalPayment;

    @OneToOne
    private Table table;

    @OneToOne
    private Restaurant restaurant;
}
