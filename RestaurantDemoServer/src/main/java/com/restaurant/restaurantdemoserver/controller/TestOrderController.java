package com.restaurant.restaurantdemoserver.controller;

import com.restaurant.restaurantdemoserver.data.dto.FoodDto;
import com.restaurant.restaurantdemoserver.data.dto.OrderDto;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.helper.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Controller
@RequestMapping("/api/test/order")
@RequiredArgsConstructor
public class TestOrderController {

    @GetMapping
    public List<OrderDto> getOrders(){
        List<FoodDto> mainCourses = List.of(FoodDto.builder().name("pizza margharita").build(), FoodDto.builder().name("pasta bolognese").build(),FoodDto.builder().name("pasta bolognese").build());
        List<FoodDto> desserts = List.of(FoodDto.builder().name("panna cotta").build(), FoodDto.builder().name("cheese cake").build());

        OrderDto order1 = OrderDto.builder()
                .table(TableDto.builder().tableName("table 1").publicId(UUID.randomUUID()).build())
                .orderedFoods(mainCourses)
                .restaurant(RestaurantDto.builder().name("vapiano").login("vapiano@gmail.com").build())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        OrderDto order2 = OrderDto.builder()
                .table(TableDto.builder().tableName("table 23").publicId(UUID.randomUUID()).build())
                .orderedFoods(desserts)
                .restaurant(RestaurantDto.builder().name("vapiano").login("vapiano@gmail.com").build())
                .orderStatus(OrderStatus.MAKING_FOOD)
                .build();

        return List.of(order1, order2);
    }
}
