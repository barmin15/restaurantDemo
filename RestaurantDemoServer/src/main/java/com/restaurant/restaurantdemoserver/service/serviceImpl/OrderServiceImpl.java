package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.OrderDto;
import com.restaurant.restaurantdemoserver.data.entity.Order;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.repository.OrderRepository;
import com.restaurant.restaurantdemoserver.repository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.repository.TableRepository;
import com.restaurant.restaurantdemoserver.service.OrderService;
import com.restaurant.restaurantdemoserver.service.converter.OrderConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderConverter orderConverter;
    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public OrderDto addOrderByTablePublicId(UUID tableId, OrderDto orderDto) {
        Table table = tableRepository.getTableByPublicId(tableId)
                .orElseThrow(() -> new AppException("Unknown Table id", HttpStatus.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(table.getRestaurant().getId())
                .orElseThrow(() -> new AppException("Unknown Restaurant", HttpStatus.NOT_FOUND));

        Order order = orderConverter.convertOrderDtoToEntity(orderDto);

        order.setTable(table);
       // order.setRestaurant(restaurant);

        order = orderRepository.save(order);

        return orderConverter.convertOrderEntityToDto(order);
    }
}
