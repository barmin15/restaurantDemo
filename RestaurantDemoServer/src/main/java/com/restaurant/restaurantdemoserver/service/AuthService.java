package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.dto.security.LoginDto;
import com.restaurant.restaurantdemoserver.data.dto.security.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    RestaurantDto findByLogin(String issuer);

    RestaurantDto register(RegisterDto signUpDTO);

    RestaurantDto login(LoginDto credentialsDTO);
}
