package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "menus")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<Drink> nonAlcoholicDrinks;

    @OneToMany
    private Set<Drink> alcoholicDrinks;

    @OneToMany
    private Set<Food> starters;

    @OneToMany
    private Set<Food> soups;

    @OneToMany
    private Set<Food> mainCourses;

    @OneToMany
    private Set<Food> desserts;
}
