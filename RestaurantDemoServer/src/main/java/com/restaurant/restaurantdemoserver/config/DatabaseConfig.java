package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.respository.*;
import com.restaurant.restaurantdemoserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@RequiredArgsConstructor
@Component
public class DatabaseConfig {
    private final FoodAllergyRepository foodAllergyRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;
    private final AuthService authService;
    private final DrinkRepository drinkRepository;

    public void configureRestaurant() {
    }

}
