package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;

    private String name;

    private Double price;

    private Integer rating;

    @ManyToMany()
    @JsonManagedReference
    private Set<FoodAllergy> foodAllergies;

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

    public void addFoodAllergy(FoodAllergy foodAllergy){
        if(foodAllergies == null) foodAllergies = new HashSet<>();

    }
}
