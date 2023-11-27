package com.restaurant.restaurantdemoserver.repository;

import com.restaurant.restaurantdemoserver.data.entity.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
    Optional<Kitchen> findByLogin(String login);
}
