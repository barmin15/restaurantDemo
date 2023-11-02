package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.entity.Guest;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
@Data
public class TableDto {
    private String tableName;
    private String login;
    private String qrCode;
    private Set<Guest> guest;

}
