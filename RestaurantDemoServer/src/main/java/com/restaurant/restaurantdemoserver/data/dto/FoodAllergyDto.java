package com.restaurant.restaurantdemoserver.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FoodAllergyDto {
    private String name;
    private UUID publicId;
}
