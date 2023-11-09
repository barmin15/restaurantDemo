package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.restaurantdemoserver.data.helper.Allergy;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "foodAllergies")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodAllergy {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;

    @Column(columnDefinition = "TEXT")
    private String iconUrlBlob;

    @Enumerated(EnumType.STRING)
    private Allergy allergy;

    @ManyToMany(mappedBy = "foodAllergies")
    @JsonBackReference
    private Set<Food> foods;

    @ManyToMany(mappedBy = "drinkAllergies")
    @JsonBackReference
    private Set<Drink> drinks;

}
