package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import com.restaurant.restaurantdemoserver.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class DatabaseConfig {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;

    public void configureRestaurant() {
        Table table = tableRepository.save(Table.builder().tableName("table").build());
        Restaurant restaurant = restaurantRepository.save(Restaurant.builder().name("Ármin res").build());


      /*  restaurant.addTable(table);*/
        //restaurantRepository.save(restaurant);
    }

}
