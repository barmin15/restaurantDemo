package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "restaurants")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float locationX;

    private Float locationY;

    @OneToOne
    private Menu menu;

    @OneToMany
    private Set<Table> tables;
}
