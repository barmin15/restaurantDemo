package com.restaurant.restaurantdemoserver.controller;

import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.dto.TablePageDto;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import com.restaurant.restaurantdemoserver.service.RestaurantService;
import com.restaurant.restaurantdemoserver.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Controller
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/{publicId}")
    public TableDto getTableByPublicId(@PathVariable UUID publicId) {
        return tableService.getTableByPublicId(publicId);
    }

    @GetMapping("/all/{login}")
    public List<TableDto> getAllByLogin(@PathVariable String login) {
        return tableService.getAll(login);
    }

    @GetMapping("/{page}/{login}")
    public TablePageDto getNthPageOfTables(@PathVariable Map<Optional<Integer>, Optional<String>> pathVarsMap) {
        String login = String.valueOf(pathVarsMap.get("login"));
        int page = Integer.parseInt(String.valueOf(pathVarsMap.get("page")));
        int limit = 20;
        return tableService.nthPageOfTablesByLogin(page * limit, login);
    }

    @PostMapping("/register/{login}")
    public void saveTablesAfterRegister(@PathVariable String login, @RequestBody List<TableDto> tables) {
        tableService.saveTablesAfterRegister(login, tables);
    }

    @PostMapping("/create/{login}")
    public TableDto addNewTableByLogin(@PathVariable String login, @RequestBody TableDto tableDto) {
        return tableService.addNewTableByLogin(login, tableDto);
    }

    @PutMapping("/update/{publicId}")
    public TableDto updateByPublicId(@PathVariable UUID publicId, @RequestBody TableDto tableDto) {
        return tableService.updateByPublicId(publicId, tableDto);
    }

    @DeleteMapping("/delete/{publicId}")
    public void deleteByPublicId(@PathVariable UUID publicId) {
        tableService.removeByPublicId(publicId);
    }

}
