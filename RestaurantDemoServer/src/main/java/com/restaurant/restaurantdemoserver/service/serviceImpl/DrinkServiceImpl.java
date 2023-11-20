package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import com.restaurant.restaurantdemoserver.data.entity.*;
import com.restaurant.restaurantdemoserver.data.helper.MenuItemType;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.repository.DrinkRepository;
import com.restaurant.restaurantdemoserver.repository.MenuRepository;
import com.restaurant.restaurantdemoserver.repository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.DrinkService;
import com.restaurant.restaurantdemoserver.service.converter.DrinkConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DrinkConverter drinkConverter;

    @Transactional
    @Override
    public Set<DrinkDto> getAlcoholicDrinksByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();

        Set<Drink> alcoholicDrinks = drinkRepository.getAllByMenu_IdAndMenuItemType(menuId, MenuItemType.Alcoholic_Drink)
                .orElseThrow(() -> new AppException("No Drinks", HttpStatus.NOT_FOUND));

        return alcoholicDrinks.stream().map(drinkConverter::convertDrinkToDto).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public Set<DrinkDto> getNonAlcoholicDrinkByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();

        Set<Drink> nonAlcoholicDrinks = drinkRepository.getAllByMenu_IdAndMenuItemType(menuId, MenuItemType.Non_Alcoholic_Drink)
                .orElseThrow(() -> new AppException("No Drinks", HttpStatus.NOT_FOUND));

        return nonAlcoholicDrinks.stream().map(drinkConverter::convertDrinkToDto).collect(Collectors.toSet());
    }

    @Override
    public DrinkDto getDrinkByPublicId(UUID publicId) {
        Drink drink = drinkRepository.getDrinkByPublicId(publicId)
                .orElseThrow(() -> new AppException("Unknown Drink", HttpStatus.NOT_FOUND));

        return drinkConverter.convertDrinkToDto(drink);
    }

    @Transactional
    @Override
    public DrinkDto insertAlcoholicDrink(String login, DrinkDto drink) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Drink converted = drinkConverter.convertDrinkDtoToEntity(drink);
        converted.setMenuItemType(MenuItemType.Alcoholic_Drink);
        converted.setPublicId(UUID.randomUUID());

        Drink inserted = drinkRepository.save(converted);

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addAlcoholicDrink(inserted);

        menuRepository.save(menu);

        return drinkConverter.convertDrinkToDto(inserted);
    }

    @Transactional
    @Override
    public DrinkDto insertNonAlcoholicDrink(String login, DrinkDto drink) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Drink converted = drinkConverter.convertDrinkDtoToEntity(drink);
        converted.setMenuItemType(MenuItemType.Non_Alcoholic_Drink);
        converted.setPublicId(UUID.randomUUID());

        Drink inserted = drinkRepository.save(converted);

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addNonAlcoholicDrink(inserted);

        menuRepository.save(menu);

        return drinkConverter.convertDrinkToDto(inserted);
    }

}
