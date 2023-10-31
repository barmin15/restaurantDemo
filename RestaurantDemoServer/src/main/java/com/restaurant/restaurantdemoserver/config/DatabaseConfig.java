package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.data.entity.*;
import com.restaurant.restaurantdemoserver.data.helper.Allergy;
import com.restaurant.restaurantdemoserver.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;


@RequiredArgsConstructor
@Component
public class DatabaseConfig {
    private final FoodAllergyRepository foodAllergyRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;

    public void configureRestaurant() {
        Table table = tableRepository.save(Table.builder().tableName("jkevkvehjkvebek").build());
        Restaurant restaurant = restaurantRepository.save(Restaurant.builder().name("Ármin res").build());
        Menu menu = menuRepository.save(Menu.builder().restaurant(restaurant).build());

        Allergy[] allergies = Allergy.values();

        for (Allergy allergy : allergies) {
            foodAllergyRepository.save(FoodAllergy.builder().allergy(allergy).build());
        }
        List<FoodAllergy> hehe = foodAllergyRepository.findAll();
        Set<FoodAllergy> h = new HashSet<>();
        hehe.forEach(hh -> h.add(hh));
        Food food = foodRepository.save(Food.builder().name("hehe").foodAllergies(h).build());

    }

}
