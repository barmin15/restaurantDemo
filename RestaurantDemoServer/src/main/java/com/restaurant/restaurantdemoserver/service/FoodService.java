package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public interface FoodService {

    Set<FoodDto> getStartersByLogin(String login);

    Set<FoodDto> getSoupsByLogin(String login);

    Set<FoodDto> getMainCoursesByLogin(String login);

    Set<FoodDto> getDessertsByLogin(String login);

    FoodDto getFoodByPublicId(UUID publicId);
}
