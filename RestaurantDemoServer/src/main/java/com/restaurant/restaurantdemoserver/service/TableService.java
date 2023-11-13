package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.dto.TablePageDto;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableService {
    void saveTablesAfterRegister(String login, List<TableDto> tables);

    List<TableDto> getAll(String login);

    TablePageDto nthPageOfTablesByLogin(int page, String login);
}
