package com.restaurant.restaurantdemoserver.respository;

import com.restaurant.restaurantdemoserver.data.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
