package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.helper.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDto {
    private UUID publicId;

    private TableDto table;

    private List<FoodDto> orderedFoods;

    private List<DrinkDto> orderedDrinks;

    private RestaurantDto restaurant;

    private OrderStatus orderStatus;
}
