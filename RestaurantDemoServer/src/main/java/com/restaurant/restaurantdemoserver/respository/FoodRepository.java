package com.restaurant.restaurantdemoserver.respository;

import com.restaurant.restaurantdemoserver.data.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
