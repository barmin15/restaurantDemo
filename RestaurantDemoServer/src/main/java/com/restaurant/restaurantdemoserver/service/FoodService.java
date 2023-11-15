package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Service
public interface FoodService {

    Set<FoodDto> getStartersByLogin(String login);

    Set<FoodDto> getSoupsByLogin(String login);

    Set<FoodDto> getMainCoursesByLogin(String login);

    Set<FoodDto> getDessertsByLogin(String login);

    FoodDto getFoodByPublicId(UUID publicId);

    FoodDto insertStarter(String login, FoodDto food);

    FoodDto insertSoup(String login, FoodDto food);

    FoodDto insertMaincourse(String login, FoodDto food);

    FoodDto insertDessert(String login, FoodDto food);

    FoodDto updateByPublicId(UUID publicId, FoodDto foodDto);

    void deleteByPublicId(UUID publicId);

    FoodDto insertPicBlobToFood(UUID publicId, MultipartFile file);
}
