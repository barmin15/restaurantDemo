package com.restaurant.restaurantdemoserver.controller;

import com.restaurant.restaurantdemoserver.data.dto.KitchenDto;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.dto.security.LoginDto;
import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import com.restaurant.restaurantdemoserver.data.entity.Kitchen;
import com.restaurant.restaurantdemoserver.security.UserAuthProvider;
import com.restaurant.restaurantdemoserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/register")
    public ResponseEntity<RestaurantDto> registerRestaurant(@RequestBody RegisterDto signUpDTO) {

        RestaurantDto restaurant = authService.register(signUpDTO);
        restaurant.setToken(userAuthProvider.createToken(restaurant.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + restaurant.getId()))
                .body(restaurant);
    }


    @PostMapping("/login")
    public ResponseEntity<RestaurantDto> loginRestaurant(@RequestBody LoginDto credentialsDTO) {

        RestaurantDto restaurant = authService.login(credentialsDTO);
        restaurant.setToken(userAuthProvider.createToken(restaurant.getLogin()));

        return ResponseEntity.ok(restaurant);
    }

    @PostMapping("/kitchen/login")
    public ResponseEntity<KitchenDto> loginKitchen(@RequestBody LoginDto credentialsDTO) {

        KitchenDto kitchenDto = authService.loginKitchen(credentialsDTO);

        kitchenDto.setToken(userAuthProvider.createToken(kitchenDto.getLogin()));

        return ResponseEntity.ok(kitchenDto);
    }


}