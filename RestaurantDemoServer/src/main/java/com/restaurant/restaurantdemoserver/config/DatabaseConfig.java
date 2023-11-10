package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.controller.AuthController;
import com.restaurant.restaurantdemoserver.controller.TableController;
import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import com.restaurant.restaurantdemoserver.data.helper.Allergy;
import com.restaurant.restaurantdemoserver.respository.*;
import com.restaurant.restaurantdemoserver.service.AuthService;
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

    public void createFoodAllergies(){
        List<Allergy> allergies = Arrays.stream(Allergy.values()).toList();

        List<FoodAllergy> allergyEntities = new ArrayList<>();

        allergies.forEach(allergy -> {
            allergyEntities.add(FoodAllergy.builder().allergy(allergy).publicId(UUID.randomUUID()).build());
        });

        foodAllergyRepository.saveAll(allergyEntities);
    }

    public void createRestaurant(){
        String pizzaMeLogin = "pizzame@gmail.com";
        RegisterDto pizzaMeRegister = RegisterDto.builder()
                .password("pizzame".toCharArray())
                .login(pizzaMeLogin)
                .restaurantName("Pizza Me")
                .build();

        authController.register(pizzaMeRegister);

        List<TableDto> tables = new ArrayList<>();

        for(int i = 1; i < 20; i++){
            tables.add(TableDto.builder().tableName("table " + i).build());
        }

        tableController.saveTablesAfterRegister(pizzaMeLogin, tables);
    }
}
