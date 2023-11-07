package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FoodDto {

    private String name;

    private Double price;

    private Integer rating;

    private Set<FoodAllergy> foodAllergies;

    private String pictureUrl;

    private String description;


}
