package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import com.restaurant.restaurantdemoserver.respository.FoodAllergyRepository;
import com.restaurant.restaurantdemoserver.service.AllergyService;
import com.restaurant.restaurantdemoserver.service.converter.FoodAllergyConverter;
import jakarta.persistence.SecondaryTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AllergyServiceImpl implements AllergyService {

    private final FoodAllergyRepository foodAllergyRepository;
    private final FoodAllergyConverter foodAllergyConverter;

    @Override
    public Set<FoodAllergyDto> getAll() {
        return foodAllergyConverter.convertAllergyEntityToDto(new HashSet<>(foodAllergyRepository.findAll()));
    }
}
