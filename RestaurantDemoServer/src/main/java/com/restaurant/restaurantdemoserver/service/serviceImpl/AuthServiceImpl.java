package com.restaurant.restaurantdemoserver.service.serviceImpl;


import com.restaurant.restaurantdemoserver.data.dto.KitchenDto;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.dto.security.LoginDto;
import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import com.restaurant.restaurantdemoserver.data.entity.Kitchen;
import com.restaurant.restaurantdemoserver.data.entity.Menu;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.repository.KitchenRepository;
import com.restaurant.restaurantdemoserver.repository.MenuRepository;
import com.restaurant.restaurantdemoserver.repository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KitchenRepository kitchenRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Override
    public RestaurantDto findByLogin(String login) {

        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return RestaurantDto.builder()
                .login(restaurant.getLogin())
                .name(restaurant.getName())
                .build();
    }

    @Transactional
    @Override
    public RestaurantDto register(RegisterDto resturantDto) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByLogin(resturantDto.getLogin());

        if (optionalRestaurant.isPresent()) throw new AppException("Already a user", HttpStatus.BAD_REQUEST);

        Restaurant restaurant = Restaurant.builder()
                .name(resturantDto.getRestaurantName())
                .login(resturantDto.getLogin())
                .password(passwordEncoder.encode(resturantDto.getPassword()))
                .build();
        UUID menuPublicId = UUID.randomUUID();

        Menu menu = Menu.builder().publicId(menuPublicId).build();

        menu = menuRepository.save(menu);
        restaurant = restaurantRepository.save(restaurant);

        restaurant.setMenu(menu);
        restaurant = restaurantRepository.save(restaurant);

        Kitchen kitchen = registerKitchen(restaurant);

        restaurant.setKitchen(kitchen);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantDto.builder()
                .login(restaurant.getLogin())
                .name(restaurant.getName())
                .build();
    }

    @Override
    public RestaurantDto login(LoginDto credentialsDTO) {
        Restaurant restaurant = restaurantRepository.findByLogin(credentialsDTO.getLogin())
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(credentialsDTO.getPassword(), restaurant.getPassword())) {

            return RestaurantDto.builder()
                    .login(restaurant.getLogin())
                    .build();
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public KitchenDto loginKitchen(LoginDto credentialsDTO) {
        Kitchen kitchen = kitchenRepository.findByLogin(credentialsDTO.getLogin())
                .orElseThrow(() -> new AppException("Unknown Kitchen", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(credentialsDTO.getPassword(), kitchen.getPassword())) {

            return KitchenDto.builder()
                    .login(kitchen.getLogin())
                    .build();
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    private Kitchen registerKitchen(Restaurant restaurant){
        String starterKitchenPassword = "kitchen";

        Kitchen kitchen = Kitchen.builder()
                .login(restaurant.getLogin())
                .password(passwordEncoder.encode(starterKitchenPassword))
                .restaurant(restaurant)
                .build();

        return kitchenRepository.save(kitchen);
    }


}

