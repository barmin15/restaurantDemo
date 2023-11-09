package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public interface DrinkService {

    Set<DrinkDto> getAlcoholicDrinksByLogin(String login);

    Set<DrinkDto> getNonAlcoholicDrinkByLogin(String login);

    DrinkDto getDrinkByPublicId(UUID publicId);

    DrinkDto insertAlcoholicDrink(String login, DrinkDto drink);

    DrinkDto insertNonAlcoholicDrink(String login, DrinkDto drink);
}
