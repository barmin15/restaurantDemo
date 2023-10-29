package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "tables")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Table {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableName;

    private Integer numberOfSeats;

    @OneToMany
    private Set<Guest> guests;

    private String qrCode;
}
