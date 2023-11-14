package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.entity.Drink;
import com.restaurant.restaurantdemoserver.data.entity.Food;
import com.restaurant.restaurantdemoserver.data.entity.Menu;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.data.helper.MenuItemType;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.repository.FoodRepository;
import com.restaurant.restaurantdemoserver.repository.MenuRepository;
import com.restaurant.restaurantdemoserver.repository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.FoodService;
import com.restaurant.restaurantdemoserver.service.converter.FoodAllergyConverter;
import com.restaurant.restaurantdemoserver.service.converter.FoodConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;
    private final FoodAllergyConverter foodAllergyConverter;
    private final FoodConverter foodConverter;

    @Override
    public Set<FoodDto> getStartersByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();

        Set<Food> starters = foodRepository.getAllByMenu_IdAndMenuItemType(menuId, MenuItemType.Starter)
                .orElseThrow(() -> new AppException("Unknown Food", HttpStatus.NOT_FOUND));

        return starters.stream().map(foodConverter::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<FoodDto> getSoupsByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();

        Set<Food> soups = foodRepository.getAllByMenu_IdAndMenuItemType(menuId, MenuItemType.Soup)
                .orElseThrow(() -> new AppException("Unknown Food", HttpStatus.NOT_FOUND));

        return soups.stream().map(foodConverter::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<FoodDto> getMainCoursesByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();

        Set<Food> mainCourses = foodRepository.getAllByMenu_IdAndMenuItemType(menuId, MenuItemType.Main_Course)
                .orElseThrow(() -> new AppException("Unknown Food", HttpStatus.NOT_FOUND));

        return mainCourses.stream().map(foodConverter::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<FoodDto> getDessertsByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();

        Set<Food> desserts = foodRepository.getAllByMenu_IdAndMenuItemType(menuId, MenuItemType.Dessert)
                .orElseThrow(() -> new AppException("Unknown Food", HttpStatus.NOT_FOUND));

        return desserts.stream().map(foodConverter::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public FoodDto getFoodByPublicId(UUID publicId) {
        Food food = foodRepository.getFoodByPublicId(publicId)
                .orElseThrow((() -> new AppException("Unknown Food", HttpStatus.NOT_FOUND)));

        return foodConverter.convertFoodToDto(food);
    }

    @Override
    public FoodDto insertStarter(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food converted = foodConverter.convertFoodDtoToEntity(food);
        converted.setMenuItemType(MenuItemType.Starter);
        converted.setPublicId(UUID.randomUUID());

        Food inserted = foodRepository.save(converted);

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addStarters(inserted);

        menuRepository.save(menu);

        return foodConverter.convertFoodToDto(inserted);
    }

    @Override
    public FoodDto insertSoup(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food converted = foodConverter.convertFoodDtoToEntity(food);
        converted.setMenuItemType(MenuItemType.Soup);
        converted.setPublicId(UUID.randomUUID());

        Food inserted = foodRepository.save(converted);
        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addSoups(inserted);

        menuRepository.save(menu);

        return foodConverter.convertFoodToDto(inserted);
    }

    @Override
    public FoodDto insertMaincourse(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food converted = foodConverter.convertFoodDtoToEntity(food);
        converted.setMenuItemType(MenuItemType.Main_Course);
        converted.setPublicId(UUID.randomUUID());

        Food inserted = foodRepository.save(converted);

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addMainCourses(inserted);

        menuRepository.save(menu);

        return foodConverter.convertFoodToDto(inserted);
    }

    @Override
    public FoodDto insertDessert(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food converted = foodConverter.convertFoodDtoToEntity(food);
        converted.setMenuItemType(MenuItemType.Dessert);
        converted.setPublicId(UUID.randomUUID());

        Food inserted = foodRepository.save(converted);

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addDesserts(inserted);

        menuRepository.save(menu);

        return foodConverter.convertFoodToDto(inserted);
    }

    @Override
    public FoodDto updateByPublicId(UUID publicId, FoodDto foodDto) {
        Food food1 = foodConverter.convertFoodDtoToEntity(foodDto);

        Food food = foodRepository.getFoodByPublicId(publicId)
                .orElseThrow(() -> new AppException("Unknown Food", HttpStatus.NOT_FOUND));

        food.setName(food1.getName());
        food.setFoodAllergies(food1.getFoodAllergies());
        food.setDescription(food1.getDescription());
        food.setPrice(food1.getPrice());

        food = foodRepository.save(food);

        return foodConverter.convertFoodToDto(food);
    }

    @Transactional
    @Override
    public void deleteByPublicId(UUID publicId) {
        foodRepository.removeByPublicId(publicId);
    }


}
