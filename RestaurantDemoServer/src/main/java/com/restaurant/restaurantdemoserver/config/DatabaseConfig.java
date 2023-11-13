package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.controller.AuthController;
import com.restaurant.restaurantdemoserver.controller.FoodController;
import com.restaurant.restaurantdemoserver.controller.TableController;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import com.restaurant.restaurantdemoserver.data.helper.Allergy;
import com.restaurant.restaurantdemoserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class DatabaseConfig {
    private final FoodAllergyRepository foodAllergyRepository;
    private final AuthController authController;
    private final TableController tableController;
    private final FoodController foodController;
    private final String LOGIN = "pizzame@gmail.com";
    private final char[] PASSWORD = "pizzame".toCharArray();

    public void createFoodAllergies(){
        List<Allergy> allergies = Arrays.stream(Allergy.values()).toList();

        List<FoodAllergy> allergyEntities = new ArrayList<>();

        allergies.forEach(allergy -> {
            allergyEntities.add(FoodAllergy.builder().allergy(allergy).publicId(UUID.randomUUID()).build());
        });

        foodAllergyRepository.saveAll(allergyEntities);
    }

    public void createRestaurant(){
        RegisterDto pizzaMeRegister = RegisterDto.builder()
                .password(PASSWORD)
                .login(LOGIN)
                .restaurantName("Pizza Me")
                .build();

        authController.register(pizzaMeRegister);

        List<TableDto> tables = new ArrayList<>();

        for(int i = 1; i < 20; i++){
            tables.add(TableDto.builder().tableName("table " + i).build());
        }

        tableController.saveTablesAfterRegister(LOGIN, tables);
    }

    public void addFoodToPizzaMe(){
        List<FoodDto> pizzas = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            pizzas.add(FoodDto.builder().name("pizza " + i).build());
        }

        pizzas.forEach(pizza -> foodController.insertMaincourse(LOGIN, pizza));
    }
}
