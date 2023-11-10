package com.restaurant.restaurantdemoserver.service.converter;

import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantConverter {

    private final MenuConverter menuConverter;
    private final TableConverter tableConverter;

    public Restaurant convertRestaurantDtoToEntity(RestaurantDto restaurantDto){
        return Restaurant.builder()
                .login(restaurantDto.getLogin())
                .name(restaurantDto.getName())
                .menu(menuConverter.convertMenuDtoToEntity(restaurantDto.getMenu()))
                .pictureUrl(restaurantDto.getImageUrl())
                .tables(restaurantDto.getTables().stream()
                        .map(tableConverter::convertTableDtoToEntity).collect(Collectors.toSet()))
                .longitude(restaurantDto.getLongitude())
                .latitude(restaurantDto.getLatitude())
                .build();
    }

    public RestaurantDto convertRestaurantEntityToDto(Restaurant restaurant){
        return RestaurantDto.builder()
                .imageUrl(restaurant.getPictureUrl())
                .login(restaurant.getLogin())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .tables(restaurant.getTables().stream()
                        .map(tableConverter::convertTableEntityToDto).collect(Collectors.toSet()))
                .name(restaurant.getName())
                .menu(menuConverter.convertMenuEntityToDto(restaurant.getMenu()))
                .build();
    }
}
