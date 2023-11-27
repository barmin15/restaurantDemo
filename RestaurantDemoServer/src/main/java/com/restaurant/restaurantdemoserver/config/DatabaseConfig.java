package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.controller.AuthController;
import com.restaurant.restaurantdemoserver.controller.DrinkController;
import com.restaurant.restaurantdemoserver.controller.FoodController;
import com.restaurant.restaurantdemoserver.controller.TableController;
import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import com.restaurant.restaurantdemoserver.data.helper.Allergy;
import com.restaurant.restaurantdemoserver.repository.*;
import jakarta.transaction.Transactional;
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
    private final DrinkController drinkController;
    private final String LOGIN = "pizzame@gmail.com";
    private final char[] PASSWORD = "pizzame".toCharArray();

    public void createFoodAllergies() {
        List<Allergy> allergies = Arrays.stream(Allergy.values()).toList();

        List<FoodAllergy> allergyEntities = new ArrayList<>();

        allergies.forEach(allergy -> {
            allergyEntities.add(FoodAllergy.builder().allergy(allergy).publicId(UUID.randomUUID()).build());
        });

        foodAllergyRepository.saveAll(allergyEntities);
    }

    public void createRestaurant() {
        RegisterDto pizzaMeRegister = RegisterDto.builder()
                .password(PASSWORD)
                .login(LOGIN)
                .restaurantName("Pizza Me")
                .build();

        authController.registerRestaurant(pizzaMeRegister);

        List<TableDto> tables = new ArrayList<>();

        for (int i = 1; i < 30; i++) {
            tables.add(TableDto.builder().tableName("table " + i).build());
        }

        tableController.saveTablesAfterRegister(LOGIN, tables);
    }

    public void addFoodToPizzaMe() {
        List<FoodDto> mainCourses = List.of(FoodDto.builder().name("pizza margharita").build(), FoodDto.builder().name("pasta bolognese").build());
        List<FoodDto> desserts = List.of(FoodDto.builder().name("panna cotta").build(), FoodDto.builder().name("cheese cake").build());
        List<FoodDto> soups = List.of(FoodDto.builder().name("tomato soup").build(), FoodDto.builder().name("soup of the season").build());
        List<FoodDto> starters = List.of(FoodDto.builder().name("insalata mista").build(), FoodDto.builder().name("insalata greek").build());
        List<DrinkDto> nonAlcoholicDrinks = List.of(DrinkDto.builder().name("san pellegrino").build(), DrinkDto.builder().name("ginger ale").build());
        List<DrinkDto> alcohlicDrinks = List.of(DrinkDto.builder().name("grappa").build(), DrinkDto.builder().name("campari").build());

        mainCourses.forEach(e -> foodController.insertMaincourse(LOGIN, e));
        desserts.forEach(e -> foodController.insertDessert(LOGIN, e));
        soups.forEach(e -> foodController.insertSoup(LOGIN, e));
        starters.forEach(e -> foodController.insertStarter(LOGIN, e));
        nonAlcoholicDrinks.forEach(e -> drinkController.insertNonAlcoholicDrink(LOGIN, e));
        alcohlicDrinks.forEach(e -> drinkController.insertAlcoholicDrink(LOGIN, e));
    }

}
