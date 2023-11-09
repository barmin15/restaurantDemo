package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.entity.Guest;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TableDto {
    private String tableName;
    private String qrCode;
    private String login;
    private List<GuestDto> guest;

}
