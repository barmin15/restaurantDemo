package com.restaurant.restaurantdemoserver.config;

import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import com.restaurant.restaurantdemoserver.data.entity.*;
import com.restaurant.restaurantdemoserver.respository.*;
import com.restaurant.restaurantdemoserver.service.AuthService;
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
    private final AuthService authService;
    private final DrinkRepository drinkRepository;

    public void configureRestaurant() {
        RegisterDto pizzaMeDto = RegisterDto.builder()
                .restaurantName("PizzaMe").login("pizzame@gmail.com").password("pizza".toCharArray()).build();
        authService.register(pizzaMeDto);

        //restaurant
        Optional<Restaurant> pizzaMe = restaurantRepository.findByLogin("pizzame@gmail.com");
        //menu
        Menu menu = menuRepository.save(Menu.builder().build());

        if (pizzaMe.isPresent()) {
            pizzaMe.get().setMenu(menu);
            restaurantRepository.save(pizzaMe.get());
        }


        Optional<Restaurant> pizzaMeRestaurant = restaurantRepository.findByLogin("pizzame@gmail.com");


        //pizzas
        Set<Food> pizzas = Set.of(
                foodRepository.save(Food.builder().name("margarita").build()),
                foodRepository.save(Food.builder().name("pepperoni").build())
        );

        //drinks
        Set<Drink> drinks = Set.of(
                drinkRepository.save(Drink.builder().name("coca-cola").build()),
                drinkRepository.save(Drink.builder().name("fanta").build())
        );

        //adding drinks and foods to menu
        if (pizzaMeRestaurant.isPresent()) {

            Long menuId = pizzaMeRestaurant.get().getMenu().getId();

            Optional<Menu> pizzaMeMenu = menuRepository.findById(menuId);

            if (pizzaMeMenu.isPresent()) {
                pizzas.forEach(pizza -> pizzaMeMenu.get().addMainCourses(pizza));
                drinks.forEach(drink -> pizzaMeMenu.get().addNonAlcoholicDrink(drink));
                menuRepository.save(pizzaMeMenu.get());
            }
        }
    }

}
