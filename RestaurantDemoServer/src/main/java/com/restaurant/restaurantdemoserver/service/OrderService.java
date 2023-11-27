package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OrderService {

    OrderDto addOrderByTablePublicId(UUID tableId, OrderDto orderDto);
}
