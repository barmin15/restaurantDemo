package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.data.entity.Menu;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import com.restaurant.restaurantdemoserver.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;


@RequiredArgsConstructor
@Component
public class DatabaseConfig {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final MenuRepository menuRepository;
    public void configureRestaurant() {
        Table table = tableRepository.save(Table.builder().tableName("jkevkvehjkvebek").build());
        Restaurant restaurant = restaurantRepository.save(Restaurant.builder().name("Ármin res").build());
        Menu menu = menuRepository.save(Menu.builder().restaurant(restaurant).build());
    }

}
