package com.restaurant.restaurantdemoserver.data.dto;

import com.restaurant.restaurantdemoserver.data.entity.Guest;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TableDto {
    private String tableName;
    private UUID publicId;
    private byte[] qrCodeBlob;
}
