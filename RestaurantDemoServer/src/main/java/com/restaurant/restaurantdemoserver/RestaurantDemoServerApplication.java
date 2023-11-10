package com.restaurant.restaurantdemoserver;

import com.restaurant.restaurantdemoserver.config.DatabaseConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RestaurantDemoServerApplication {

    private final DatabaseConfig databaseConfig;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantDemoServerApplication.class, args);
    }



    @PostConstruct
    public void configure(){
       databaseConfig.createFoodAllergies();
       databaseConfig.createRestaurant();
    }


}
