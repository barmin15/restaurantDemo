package com.restaurant.restaurantdemoserver.respository;

import com.restaurant.restaurantdemoserver.data.entity.Restaurant;
import com.restaurant.restaurantdemoserver.data.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByLogin(String login);
}
