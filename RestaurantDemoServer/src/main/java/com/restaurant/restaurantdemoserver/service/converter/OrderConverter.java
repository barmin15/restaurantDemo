package com.restaurant.restaurantdemoserver.service.converter;


import com.restaurant.restaurantdemoserver.data.dto.OrderDto;
import com.restaurant.restaurantdemoserver.data.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderConverter {
    private final TableConverter tableConverter;
    private final DrinkConverter drinkConverter;
    private final FoodConverter foodConverter;
    private final RestaurantConverter restaurantConverter;

    public Order convertOrderDtoToEntity(OrderDto orderDto) {
        return Order.builder()
             //   .table(tableConverter.convertTableDtoToEntity(orderDto.getTable()))
                .orderedDrinks(orderDto.getOrderedDrinks().stream().map(drinkConverter::convertDrinkDtoToEntity).toList())
                .orderedFoods(orderDto.getOrderedFoods().stream().map(foodConverter::convertFoodDtoToEntity).toList())
                .orderStatus(orderDto.getOrderStatus())
                .publicId(orderDto.getPublicId() == null ? UUID.randomUUID() : orderDto.getPublicId())
                .build();
    }

    public OrderDto convertOrderEntityToDto(Order order) {
        return OrderDto.builder()
                .table(tableConverter.convertTableEntityToDto(order.getTable()))
               // .restaurant(restaurantConverter.convertRestaurantEntityToDto(order.getRestaurant()))
                .orderedDrinks(order.getOrderedDrinks().stream().map(drinkConverter::convertDrinkToDto).toList())
                .orderedFoods(order.getOrderedFoods().stream().map(foodConverter::convertFoodToDto).toList())
                .publicId(order.getPublicId())
                .build();
    }
}
