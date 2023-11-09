package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.entity.Menu;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.respository.MenuRepository;
import com.restaurant.restaurantdemoserver.respository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.respository.TableRepository;
import com.restaurant.restaurantdemoserver.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final MenuRepository menuRepository;

    @Override
    public void saveTablesAfterRegister(String login, List<TableDto> tables) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(()-> new AppException("Unknown Login", HttpStatus.NOT_FOUND));

       // Menu menu = menuRepository.save(Menu.builder().build());

       // restaurant.setMenu(menu);
        List<Table> tablesList = tables.stream().map(this::convertTableDtoNameToEntityName).toList();
        tablesList.forEach(restaurant::addTable);

        restaurantRepository.save(restaurant);

    }

    private Table convertTableDtoNameToEntityName(TableDto tableDto){
        return Table.builder()
                .tableName(tableDto.getTableName())
                .build();
    }
}
