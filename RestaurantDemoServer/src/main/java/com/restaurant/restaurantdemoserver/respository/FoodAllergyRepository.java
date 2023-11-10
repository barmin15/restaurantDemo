package com.restaurant.restaurantdemoserver.respository;

import com.restaurant.restaurantdemoserver.data.entity.FoodAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface FoodAllergyRepository extends JpaRepository<FoodAllergy, Long> {

    public Optional<Set<FoodAllergy>> getFoodAllergiesByPublicIdIsIn(Set<UUID> publicIds);
}
