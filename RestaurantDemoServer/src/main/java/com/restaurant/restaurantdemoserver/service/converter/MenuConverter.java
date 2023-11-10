package com.restaurant.restaurantdemoserver.service.converter;

import com.restaurant.restaurantdemoserver.data.dto.MenuDto;
import com.restaurant.restaurantdemoserver.data.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuConverter {
    private final FoodConverter foodConverter;
    private final DrinkConverter drinkConverter;

    public Menu convertMenuDtoToEntity(MenuDto menuDto){
        return Menu.builder()
                .publicId(menuDto.getPublicId())
                .mainCourses(menuDto.getMainCourses().stream()
                        .map(foodConverter::convertFoodDtoToEntity).collect(Collectors.toSet()))
                .soups(menuDto.getSoups().stream()
                        .map(foodConverter::convertFoodDtoToEntity).collect(Collectors.toSet()))
                .desserts(menuDto.getDesserts().stream()
                        .map(foodConverter::convertFoodDtoToEntity).collect(Collectors.toSet()))
                .starters(menuDto.getStarters().stream()
                        .map(foodConverter::convertFoodDtoToEntity).collect(Collectors.toSet()))
                .alcoholicDrinks(menuDto.getAlcoholicDrinks().stream()
                        .map(drinkConverter::convertDrinkDtoToEntity).collect(Collectors.toSet()))
                .nonAlcoholicDrinks(menuDto.getNonAlcoholicDrinks().stream()
                        .map(drinkConverter::convertDrinkDtoToEntity).collect(Collectors.toSet()))
                .build();
    }

    public MenuDto convertMenuEntityToDto(Menu menu){
        return MenuDto.builder()
                .soups(menu.getSoups().stream()
                        .map(foodConverter::convertFoodToDto).collect(Collectors.toSet()))
                .starters(menu.getStarters().stream()
                        .map(foodConverter::convertFoodToDto).collect(Collectors.toSet()))
                .mainCourses(menu.getMainCourses().stream()
                        .map(foodConverter::convertFoodToDto).collect(Collectors.toSet()))
                .desserts(menu.getDesserts().stream()
                        .map(foodConverter::convertFoodToDto).collect(Collectors.toSet()))
                .alcoholicDrinks(menu.getAlcoholicDrinks().stream()
                        .map(drinkConverter::convertDrinkToDto).collect(Collectors.toSet()))
                .nonAlcoholicDrinks(menu.getNonAlcoholicDrinks().stream()
                        .map(drinkConverter::convertDrinkToDto).collect(Collectors.toSet()))
                .build();
    }
}
