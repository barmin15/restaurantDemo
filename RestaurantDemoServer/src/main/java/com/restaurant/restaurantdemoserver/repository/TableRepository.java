package com.restaurant.restaurantdemoserver.repository;

import com.restaurant.restaurantdemoserver.data.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {

    public List<Table> getAllByRestaurantId(Long restaurantId);
}
