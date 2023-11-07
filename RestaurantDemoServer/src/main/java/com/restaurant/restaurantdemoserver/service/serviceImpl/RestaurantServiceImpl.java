package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.respository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantDto getRestaurantByLogin(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        return convertRestaurantToDto(restaurant);
    }


    private RestaurantDto convertRestaurantToDto(Restaurant restaurant){
        return RestaurantDto.builder()
                .menu(restaurant.getMenu())
                .name(restaurant.getName())
                .tables(restaurant.getTables())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .imageUrl(restaurant.getPictureUrl())
                .build();
    }
}
