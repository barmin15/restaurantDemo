package com.restaurant.restaurantdemoserver.repository;

import com.restaurant.restaurantdemoserver.data.entity.Drink;
import com.restaurant.restaurantdemoserver.data.helper.MenuItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> getDrinkByPublicId(UUID publicId);

    Optional<Set<Drink>> getAllByMenu_IdAndMenuItemType(Long menuId, MenuItemType menuItemType);
}
