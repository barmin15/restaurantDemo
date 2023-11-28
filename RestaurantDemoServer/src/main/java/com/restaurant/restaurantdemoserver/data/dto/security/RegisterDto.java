package com.restaurant.restaurantdemoserver.data.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterDto {
    private String login;
    private String restaurantName;
    private String password;
}
