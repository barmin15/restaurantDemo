package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "restaurants")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double longitude;

    private Double latitude;

    @OneToOne
    private Menu menu;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Table> tables;


    public void addTable(Table table){
        table.setRestaurant(this);
        if(tables == null) tables = new HashSet<>();
        tables.add(table);
    }
}
