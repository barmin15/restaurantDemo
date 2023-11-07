package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.entity.Drink;
import com.restaurant.restaurantdemoserver.data.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MenuDto {
    private String login;
    private Set<Drink> alcoholicDrinks;
    private Set<Drink> nonAlcoholicDrinks;
    private Set<Food> starters;
    private Set<Food> soups;
    private Set<Food> mainCourses;
    private Set<Food> desserts;
}
