package com.restaurant.restaurantdemoserver.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurant.restaurantdemoserver.data.entity.Menu;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RestaurantDto {
    private Long id;

    private String name;

    private Double longitude;

    private Double latitude;

    private MenuDto menu;

    private String login;

    private String token;

    private String imageUrl;

    private Set<TableDto> tables;
}
