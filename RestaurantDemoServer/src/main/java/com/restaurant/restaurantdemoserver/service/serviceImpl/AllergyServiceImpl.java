package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import com.restaurant.restaurantdemoserver.repository.FoodAllergyRepository;
import com.restaurant.restaurantdemoserver.service.AllergyService;
import com.restaurant.restaurantdemoserver.service.converter.FoodAllergyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
