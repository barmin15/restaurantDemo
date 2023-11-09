package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.FoodAllergyDto;
import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.entity.Food;
import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import com.restaurant.restaurantdemoserver.data.entity.Menu;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.respository.FoodRepository;
import com.restaurant.restaurantdemoserver.respository.MenuRepository;
import com.restaurant.restaurantdemoserver.respository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;

    @Override
    public Set<FoodDto> getStartersByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        Set<Food> starters = menu.getStarters();

        return starters.stream().map(this::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<FoodDto> getSoupsByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        Set<Food> soups = menu.getSoups();

        return soups.stream().map(this::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<FoodDto> getMainCoursesByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        Set<Food> mainCourses = menu.getMainCourses();

        return mainCourses.stream().map(this::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<FoodDto> getDessertsByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Long menuId = restaurant.getMenu().getId();
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        Set<Food> desserts = menu.getDesserts();

        return desserts.stream().map(this::convertFoodToDto).collect(Collectors.toSet());
    }

    @Override
    public FoodDto getFoodByPublicId(UUID publicId) {
        Food food = foodRepository.getFoodByPublicId(publicId)
                .orElseThrow((() -> new AppException("Unknown Food", HttpStatus.NOT_FOUND)));

        return convertFoodToDto(food);
    }

    @Override
    public FoodDto insertStarter(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food inserted = foodRepository.save(convertFoodDtoToEntity(food));

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addStarters(inserted);

        menuRepository.save(menu);

        return convertFoodToDto(inserted);
    }

    @Override
    public FoodDto insertSoup(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food inserted = foodRepository.save(convertFoodDtoToEntity(food));

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addSoups(inserted);

        menuRepository.save(menu);

        return convertFoodToDto(inserted);
    }

    @Override
    public FoodDto insertMaincourse(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food inserted = foodRepository.save(convertFoodDtoToEntity(food));

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addMainCourses(inserted);

        menuRepository.save(menu);

        return convertFoodToDto(inserted);
    }

    @Override
    public FoodDto insertDessert(String login, FoodDto food) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Food inserted = foodRepository.save(convertFoodDtoToEntity(food));

        Long menuId = restaurant.getMenu().getId();

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException("Unknown Menu", HttpStatus.NOT_FOUND));

        menu.addDesserts(inserted);

        menuRepository.save(menu);

        return convertFoodToDto(inserted);
    }

    private FoodDto convertFoodToDto(Food food) {
        return FoodDto.builder()
                .name(food.getName())
                .foodAllergies(convertAllergyToDto(food.getFoodAllergies()))
                .description(food.getDescription())
                .pictureUrl(food.getPictureUrl())
                .price(food.getPrice())
                .rating(food.getRating())
                .publicId(food.getPublicId())
                .build();
    }

    private Set<FoodAllergyDto> convertAllergyToDto(Set<FoodAllergy> allergies) {
        Set<FoodAllergyDto> allergyDtos = new HashSet<>();
        allergies.forEach(allergy -> allergyDtos.add(FoodAllergyDto.builder()
                .name(allergy.getAllergy().toString())
                .publicId(allergy.getPublicId())
                .build()));

        return allergyDtos;
    }

    private Food convertFoodDtoToEntity(FoodDto foodDto) {
        return Food.builder()
                .name(foodDto.getName())
                .description(foodDto.getDescription())
                .pictureUrl(foodDto.getPictureUrl())
                .rating(foodDto.getRating())
                .price(foodDto.getPrice())
                .build();
    }
}
