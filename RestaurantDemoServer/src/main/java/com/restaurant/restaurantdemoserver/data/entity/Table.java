package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

    private String qrCode;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Guest> guests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Restaurant restaurant;

    public void addRestaurant(Restaurant restaurant){
        restaurant.addTable(this);
    }

    public void addGuest(Guest guest){
        if(guests == null) guests = new HashSet<>();
        guests.add(guest);
        guest.setTable(this);
    }

}
