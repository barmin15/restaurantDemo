package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.entity.*;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.respository.DrinkRepository;
import com.restaurant.restaurantdemoserver.respository.FoodRepository;
import com.restaurant.restaurantdemoserver.respository.MenuRepository;
import com.restaurant.restaurantdemoserver.respository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.DrinkService;
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

    @Override
    public DrinkDto getDrinkByPublicId(UUID publicId) {
        Drink drink = drinkRepository.getDrinkByPublicId(publicId)
                .orElseThrow(() -> new AppException("Unknown Drink", HttpStatus.NOT_FOUND));

        return convertDrinkToDto(drink);
    }

    @Override
    public DrinkDto insertAlcoholicDrink(String login, DrinkDto drink) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Drink inserted = drinkRepository.save(convertDrinkDtoToEntity(drink));

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addAlcoholicDrink(inserted);

        menuRepository.save(menu);

        return convertDrinkToDto(inserted);
    }

    @Override
    public DrinkDto insertNonAlcoholicDrink(String login, DrinkDto drink) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Drink inserted = drinkRepository.save(convertDrinkDtoToEntity(drink));

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addNonAlcoholicDrink(inserted);

        menuRepository.save(menu);

        return convertDrinkToDto(inserted);
    }

    private DrinkDto convertDrinkToDto(Drink drink) {
        return DrinkDto.builder()
                .name(drink.getName())
                .drinkAllergies(convertAllergyToDto(drink.getDrinkAllergies()))
                .description(drink.getDescription())
                .pictureUrl(drink.getPictureUrl())
                .price(drink.getPrice())
                .rating(drink.getRating())
                .publicId(drink.getPublicId())
                .build();
    }

    private Set<FoodAllergyDto> convertAllergyToDto(Set<FoodAllergy> allergies) {
        Set<FoodAllergyDto> allergyDtos = new HashSet<>();
        allergies.forEach(allergy -> allergyDtos.add(FoodAllergyDto.builder()
                .name(allergy.getAllergy().toString())
                .publicId(allergy.getPublicId())
                .build()));

        return allergyDtos;
    }

    private Drink convertDrinkDtoToEntity(DrinkDto drinkDto) {
        return Drink.builder()
                .name(drinkDto.getName())
                .description(drinkDto.getDescription())
                .pictureUrl(drinkDto.getPictureUrl())
                .rating(drinkDto.getRating())
                .price(drinkDto.getPrice())
                .build();
    }
}
