package com.restaurant.restaurantdemoserver.service.converter;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.data.entity.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DrinkConverter {

    private final FoodAllergyConverter foodAllergyConverter;

    public DrinkDto convertDrinkToDto(Drink drink) {
        return DrinkDto.builder()
                .name(drink.getName())
                .drinkAllergies(foodAllergyConverter.convertAllergyEntityToDto(drink.getDrinkAllergies()))
                .description(drink.getDescription())
                .pictureUrl(drink.getPictureUrl())
                .price(drink.getPrice())
                .rating(drink.getRating())
                .publicId(drink.getPublicId())
                .build();
    }

    public Drink convertDrinkDtoToEntity(DrinkDto drink) {
        return Drink.builder()
                .name(drink.getName())
                .drinkAllergies(foodAllergyConverter.convertFoodAllergyDtoToEntity(drink.getDrinkAllergies()))
                .description(drink.getDescription())
                .pictureUrl(drink.getPictureUrl())
                .rating(drink.getRating())
                .price(drink.getPrice())
                .build();
    }
}
