package com.restaurant.restaurantdemoserver.respository;

import com.restaurant.restaurantdemoserver.data.entity.Drink;
import com.restaurant.restaurantdemoserver.data.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> getDrinkByPublicId(UUID publicId);
}
