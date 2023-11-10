package com.restaurant.restaurantdemoserver.controller;

import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.data.dto.TableDto;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import com.restaurant.restaurantdemoserver.service.RestaurantService;
import com.restaurant.restaurantdemoserver.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Controller
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/all/{login}")
    public List<TableDto> getAllByLogin (@PathVariable String login){
        return tableService.getAll(login);
    }


    @PostMapping("/register/{login}")
    public void saveTablesAfterRegister(@PathVariable String login, @RequestBody List<TableDto> tables) {
       tableService.saveTablesAfterRegister(login, tables);
    }


}
