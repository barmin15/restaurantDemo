package com.restaurant.restaurantdemoserver.repository;

import com.restaurant.restaurantdemoserver.data.entity.Food;
import com.restaurant.restaurantdemoserver.data.helper.MenuItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> getFoodByPublicId(UUID publicId);

    Optional<Set<Food>> getAllByMenu_IdAndMenuItemType(Long menuId, MenuItemType menuItemType);

}
