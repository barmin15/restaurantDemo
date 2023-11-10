package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AllergyService {
    Set<FoodAllergyDto> getAll();
}
