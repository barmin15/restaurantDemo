package com.restaurant.restaurantdemoserver.data.dto;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DrinkDto {

    private UUID publicId;

    private String name;

    private int rating;

    private Double price;

    private String pictureUrl;

    private String description;

    private Set<FoodAllergyDto> drinkAllergies;
}
