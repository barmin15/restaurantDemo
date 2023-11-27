package com.restaurant.restaurantdemoserver.controller;

import com.restaurant.restaurantdemoserver.data.dto.OrderDto;
import com.restaurant.restaurantdemoserver.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Controller
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add/{tablePublicId}")
    public OrderDto addOrderByTableId(@PathVariable UUID tablePublicId, @RequestBody OrderDto orderDto){
        return orderService.addOrderByTablePublicId(tablePublicId, orderDto);
    }
}
