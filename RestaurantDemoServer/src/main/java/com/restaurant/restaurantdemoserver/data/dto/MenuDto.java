package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.entity.Drink;
import com.restaurant.restaurantdemoserver.data.entity.Food;
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
public class MenuDto {
    private String login;
    private UUID publicId;
    private Set<DrinkDto> alcoholicDrinks;
    private Set<DrinkDto> nonAlcoholicDrinks;
    private Set<FoodDto> starters;
    private Set<FoodDto> soups;
    private Set<FoodDto> mainCourses;
    private Set<FoodDto> desserts;
}
