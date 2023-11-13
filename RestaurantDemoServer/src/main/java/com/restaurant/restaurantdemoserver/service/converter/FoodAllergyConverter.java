package com.restaurant.restaurantdemoserver.service.converter;

import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.repository.FoodAllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FoodAllergyConverter {

    private final FoodAllergyRepository foodAllergyRepository;

    public Set<FoodAllergyDto> convertAllergyEntityToDto(Set<FoodAllergy> allergies) {
        Set<FoodAllergyDto> allergyDtos = new HashSet<>();
        allergies.forEach(allergy -> allergyDtos.add(FoodAllergyDto.builder()
                .name(allergy.getAllergy().toString())
                .publicId(allergy.getPublicId())
                .build()));

        return allergyDtos;
    }

    public Set<FoodAllergy> convertFoodAllergyDtoToEntity(Set<FoodAllergyDto> foodAllergyDto){
        Set<UUID> publicIds = new HashSet<>();

        if(foodAllergyDto != null){
            foodAllergyDto.forEach(foodAllergy -> publicIds.add(foodAllergy.getPublicId()));

            return foodAllergyRepository.getFoodAllergiesByPublicIdIsIn(publicIds)
                    .orElseThrow(() -> new AppException("Unknown FoodAllergy Public id", HttpStatus.NOT_FOUND));
        }
        return new HashSet<>();
    }
}
