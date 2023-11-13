package com.restaurant.restaurantdemoserver.controller;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;


@RestController
@Controller
@RequestMapping("/api/drink")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping("/alcoholic-drinks/{login}")
    public Set<DrinkDto> getAlcoholicDrinksByLogin(@PathVariable String login){
        return drinkService.getAlcoholicDrinksByLogin(login);
    }

    @GetMapping("/non-alcoholic-drinks/{login}")
    public Set<DrinkDto> getNonAlcoholicDrinkByLogin(@PathVariable String login){
        return drinkService.getNonAlcoholicDrinkByLogin(login);
    }

    @GetMapping("/{publicId}")
    public DrinkDto getDrinkByPublicId(@PathVariable UUID publicId){
        return drinkService.getDrinkByPublicId(publicId);
    }

    @PostMapping("/alcoholic-drinks/{login}")
    public DrinkDto insertAlcoholicDrink(@PathVariable String login, @RequestBody DrinkDto drink){
        return drinkService.insertAlcoholicDrink(login, drink);
    }
    @PostMapping("/non-alcoholic-drinks/{login}")
    public DrinkDto insertNonAlcoholicDrink(@PathVariable String login, @RequestBody DrinkDto drink){
        return drinkService.insertNonAlcoholicDrink(login, drink);
    }


}


