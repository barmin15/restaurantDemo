package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "drinks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Drink {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;

    private String name;

    private int rating;

    private Double price;

    @Column(columnDefinition = "TEXT")
    private String pictureUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Guest guest;

    @ManyToMany()
    @JsonManagedReference
    private Set<FoodAllergy> drinkAllergies;
}
