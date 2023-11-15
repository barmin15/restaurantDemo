package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.dto.TablePageDto;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TableService {
    void saveTablesAfterRegister(String login, List<TableDto> tables);

    List<TableDto> getAll(String login);

    TablePageDto nthPageOfTablesByLogin(int page, String login);

    TableDto addNewTableByLogin(String login, TableDto tableDto);

    TableDto getTableByPublicId(UUID publicId);

    TableDto updateByPublicId(UUID publicId, TableDto tableDto);

    void removeByPublicId(UUID publicId);
}
