package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "foods")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Food {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private Set<Ingredient> Ingredients;

    @Column(columnDefinition = "TEXT")
    private String pictureUrl;

    private Float price;

    private Byte rating;

    @OneToMany
    private Set<BeveragePreference> beveragePreferences;

    @OneToMany
    private Set<Allergy> allergies;

    @Column(columnDefinition = "TEXT")
    private String description;
}

