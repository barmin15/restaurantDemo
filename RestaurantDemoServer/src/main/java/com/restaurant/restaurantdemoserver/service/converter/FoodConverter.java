package com.restaurant.restaurantdemoserver.service.converter;

import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.entity.Food;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class FoodConverter {

    private final FoodAllergyConverter foodAllergyConverter;

    public FoodDto convertFoodToDto(Food food) {
        return FoodDto.builder()
                .name(food.getName())
                .allergies(foodAllergyConverter.convertAllergyEntityToDto(food.getFoodAllergies()))
                .description(food.getDescription())
                .pictureBlob(food.getPictureBlob())
                .price(food.getPrice())
                .rating(food.getRating())
                .publicId(food.getPublicId())
                .build();
    }

    public Food convertFoodDtoToEntity(FoodDto foodDto) {
        return Food.builder()
                .name(foodDto.getName())
                .foodAllergies(foodAllergyConverter.convertFoodAllergyDtoToEntity(foodDto.getAllergies()))
                .description(foodDto.getDescription())
                .pictureBlob(foodDto.getPictureBlob())
                .rating(foodDto.getRating())
                .price(foodDto.getPrice())
                .build();
    }
}
