package com.restaurant.restaurantdemoserver.repository;

import com.restaurant.restaurantdemoserver.data.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {

    public List<Table> getAllByRestaurantId(Long restaurantId);

    public Optional<Table> getTableByPublicId(UUID publicId);

    void removeByPublicId(UUID publicId);
}
