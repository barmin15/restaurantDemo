package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.entity.Drink;
import com.restaurant.restaurantdemoserver.data.entity.Food;

import java.util.Set;

public class MenuDto {
    private String login;
    private Set<Drink> alcoholicDrinks;
    private Set<Drink> nonAlcoholicDrinks;
    private Set<Food> starters;
    private Set<Food> soups;
    private Set<Food> mainCourses;
    private Set<Food> desserts;
}
