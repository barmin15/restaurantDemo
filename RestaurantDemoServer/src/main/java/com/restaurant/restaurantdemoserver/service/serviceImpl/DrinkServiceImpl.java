package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.entity.Drink;
import com.restaurant.restaurantdemoserver.data.entity.Food;
import com.restaurant.restaurantdemoserver.data.entity.Menu;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.respository.DrinkRepository;
import com.restaurant.restaurantdemoserver.respository.FoodRepository;
import com.restaurant.restaurantdemoserver.respository.MenuRepository;
import com.restaurant.restaurantdemoserver.respository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DrinkRepository drinkRepository;


    @Override
    public Set<DrinkDto> getAlcoholicDrinksByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        Set<Drink> alcoholicDrinks = menu.getAlcoholicDrinks();

        return alcoholicDrinks.stream().map(this::convertDrinkToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<DrinkDto> getNonAlcoholicDrinkByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        Set<Drink> nonAlcoholicDrinks = menu.getNonAlcoholicDrinks();

        return nonAlcoholicDrinks.stream().map(this::convertDrinkToDto).collect(Collectors.toSet());
    }

    private DrinkDto convertDrinkToDto(Drink drink) {
        return DrinkDto.builder()
                .name(drink.getName())
                .drinkAllergies(drink.getDrinkAllergies())
                .description(drink.getDescription())
                .pictureUrl(drink.getPictureUrl())
                .price(drink.getPrice())
                .rating(drink.getRating())
                .build();
    }
}
