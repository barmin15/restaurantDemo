package com.restaurant.restaurantdemoserver.data.entity.Food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "mainCourses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainCourse {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
