package com.restaurant.restaurantdemoserver.service.serviceImpl;

import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.dto.TablePageDto;
import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import com.restaurant.restaurantdemoserver.exception.AppException;
import com.restaurant.restaurantdemoserver.repository.MenuRepository;
import com.restaurant.restaurantdemoserver.repository.RestaurantRepository;
import com.restaurant.restaurantdemoserver.repository.TableRepository;
import com.restaurant.restaurantdemoserver.service.TableService;
import com.restaurant.restaurantdemoserver.service.converter.TableConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final MenuRepository menuRepository;
    private final TableConverter tableConverter;

    @Override
    public void saveTablesAfterRegister(String login, List<TableDto> tables) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(()-> new AppException("Unknown Login", HttpStatus.NOT_FOUND));

        List<Table> tablesList = tables.stream().map(tableConverter::convertTableDtoNameToEntityName).toList();
        tablesList.forEach(restaurant::addTable);

        restaurantRepository.save(restaurant);

    }

    @Override
    public List<TableDto> getAll(String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(()-> new AppException("Unknown Login", HttpStatus.NOT_FOUND));

        Long restaurantId = restaurant.getId();

        List<Table> tables = tableRepository.getAllByRestaurantId(restaurantId);

        Collections.sort(tables);

        return tables.stream().map(tableConverter::convertTableEntityToDto).toList();
    }

    @Override
    public TablePageDto nthPageOfTablesByLogin(int page, String login) {
        Restaurant restaurant = restaurantRepository.findByLogin(login)
                .orElseThrow(()-> new AppException("Unknown Login", HttpStatus.NOT_FOUND));

        Long restaurantId = restaurant.getId();

        List<Table> tables = tableRepository.getAllByRestaurantId(restaurantId);

        Collections.sort(tables);

        TablePageDto tablePageDto = TablePageDto.builder().allTableCount(tables.size()).build();

        List<TableDto> tableDtos = new ArrayList<>();

        for(int i = 0; i < page; i++){
            if(i < tables.size()){
                tableDtos.add(tableConverter.convertTableEntityToDto(tables.get(i)));
            }
        }

        tablePageDto.setTables(tableDtos);
        return tablePageDto;
    }


}
