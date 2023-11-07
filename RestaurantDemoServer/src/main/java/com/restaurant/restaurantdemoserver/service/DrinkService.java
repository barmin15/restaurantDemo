package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface DrinkService {

    Set<DrinkDto> getAlcoholicDrinksByLogin(String login);

    Set<DrinkDto> getNonAlcoholicDrinkByLogin(String login);
}
