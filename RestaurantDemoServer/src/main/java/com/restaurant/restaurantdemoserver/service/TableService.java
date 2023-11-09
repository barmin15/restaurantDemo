package com.restaurant.restaurantdemoserver.service;

import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableService {
    void saveTablesAfterRegister(String login, List<TableDto> tables);
}
