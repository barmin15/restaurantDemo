package com.restaurant.restaurantdemoserver.controller;

import com.restaurant.restaurantdemoserver.data.dto.DrinkDto;
import com.restaurant.restaurantdemoserver.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/drink")
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping("/alcoholic/{login}")
    public Set<DrinkDto> getAlcoholicDrinksByLogin(@PathVariable String login){
        return drinkService.getAlcoholicDrinksByLogin(login);
    }

    @GetMapping("/nonAlcoholic/{login}")
    public Set<DrinkDto> getNonAlcoholicDrinkByLogin(@PathVariable String login){
        return drinkService.getNonAlcoholicDrinkByLogin(login);
    }

    @GetMapping("/{publicId}")
    public DrinkDto getDrinkByPublicId(@PathVariable UUID publicId){
        return drinkService.getDrinkByPublicId(publicId);
    }


}
