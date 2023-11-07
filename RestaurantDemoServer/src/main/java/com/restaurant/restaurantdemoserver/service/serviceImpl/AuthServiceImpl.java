package com.restaurant.restaurantdemoserver.service.serviceImpl;


import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.dto.security.LoginDto;
import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.respository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDto findByLogin(String login) {

        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return RestaurantDto.builder()
                .login(restaurant.getLogin())
                .name(restaurant.getName())
                .build();
    }

    @Override
    public RestaurantDto register(RegisterDto resturantDto) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByLogin(resturantDto.getLogin());

        if (optionalRestaurant.isPresent()) throw new AppException("Already a user", HttpStatus.BAD_REQUEST);

        Restaurant restaurant = Restaurant.builder()
                .name(resturantDto.getRestaurantName())
                .login(resturantDto.getLogin())
                .password(passwordEncoder.encode(CharBuffer.wrap(resturantDto.getPassword())))
                .build();

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantDto.builder()
                .login(restaurant.getLogin())
                .name(restaurant.getName())
                .build();
    }

    @Override
    public RestaurantDto login(LoginDto credentialsDTO) {
        Restaurant restaurant = restaurantRepository.findByLogin(credentialsDTO.getLogin())
                .orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), restaurant.getPassword())) {

            return RestaurantDto.builder()
                    .login(restaurant.getLogin())
                    .name(restaurant.getName())
                    .menu(restaurant.getMenu())
                    .tables(restaurant.getTables())
                    .latitude(restaurant.getLatitude())
                    .longitude(restaurant.getLongitude())
                    .build();
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
}

