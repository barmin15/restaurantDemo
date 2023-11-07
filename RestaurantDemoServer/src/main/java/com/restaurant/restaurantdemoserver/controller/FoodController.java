package com.restaurant.restaurantdemoserver.controller;


import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/starters/{login}")
    public Set<FoodDto> getStartersByLogin(@PathVariable String login){
      return foodService.getStartersByLogin(login);
    }

    @GetMapping("/soups/{login}")
    public Set<FoodDto> getSoupsByLogin(@PathVariable String login){
        return foodService.getSoupsByLogin(login);
    }
    @GetMapping("/mainCourses/{login}")
    public Set<FoodDto> getMainCoursesByLogin(@PathVariable String login){
        return foodService.getMainCoursesByLogin(login);
    }
    @GetMapping("/desserts/{login}")
    public Set<FoodDto> getDessertsByLogin(@PathVariable String login){
        return foodService.getDessertsByLogin(login);
    }
}
