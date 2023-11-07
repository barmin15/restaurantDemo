package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import org.springframework.stereotype.Service;


@Service
public interface RestaurantService {
    RestaurantDto getRestaurantByLogin(String login);
}
