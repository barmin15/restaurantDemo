package com.restaurant.restaurantdemoserver.controller;


import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.entity.Food;
import com.restaurant.restaurantdemoserver.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

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
    @GetMapping("/main-courses/{login}")
    public Set<FoodDto> getMainCoursesByLogin(@PathVariable String login){
        return foodService.getMainCoursesByLogin(login);
    }
    @GetMapping("/desserts/{login}")
    public Set<FoodDto> getDessertsByLogin(@PathVariable String login){
        return foodService.getDessertsByLogin(login);
    }

    @GetMapping("/{publicId}")
    public FoodDto getFoodByPublicId(@PathVariable UUID publicId){
       return foodService.getFoodByPublicId(publicId);
    }

    @PostMapping("/starters/{login}")
    public FoodDto insertStarter(@PathVariable String login, @RequestBody FoodDto food){
        return foodService.insertStarter(login, food);
    }
    @PostMapping("/soups/{login}")
    public FoodDto insertSoup(@PathVariable String login, @RequestBody FoodDto food){
        return foodService.insertSoup(login, food);
    }
    @PostMapping("/main-courses/{login}")
    public FoodDto insertMaincourse(@PathVariable String login, @RequestBody FoodDto food){
        return foodService.insertMaincourse(login, food);
    }
    @PostMapping("/desserts/{login}")
    public FoodDto insertDessert(@PathVariable String login, @RequestBody FoodDto food){
        return foodService.insertDessert(login, food);
    }

    @PutMapping("update/{publicId}")
    public FoodDto updateFoodByPublicId(@PathVariable UUID publicId, @RequestBody FoodDto foodDto){
        return foodService.updateByPublicId(publicId, foodDto);
    }
}
