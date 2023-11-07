package com.restaurant.restaurantdemoserver.data.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ErrorDto {

    private String message;

}
