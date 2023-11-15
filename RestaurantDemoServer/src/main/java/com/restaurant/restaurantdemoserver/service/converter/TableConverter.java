package com.restaurant.restaurantdemoserver.service.converter;


import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TableConverter {

    public Table convertTableDtoNameToEntityName(TableDto tableDto){
        return Table.builder()
                .tableName(tableDto.getTableName())
                .build();
    }

    public TableDto convertTableEntityToDto(Table table){
        return TableDto.builder()
                .tableName(table.getTableName())
                .qrCodeBlob(table.getQrCodeBlob())
                .publicId(table.getPublicId())
                .build();
    }

    public Table convertTableDtoToEntity(TableDto tableDto){
        return Table.builder()
                .tableName(tableDto.getTableName())
                .publicId(tableDto.getPublicId())
                .qrCodeBlob(tableDto.getQrCodeBlob())
                .publicId(tableDto.getPublicId())
                .build();
    }
}
