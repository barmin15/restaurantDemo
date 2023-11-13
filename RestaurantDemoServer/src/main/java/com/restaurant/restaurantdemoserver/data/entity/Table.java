package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tables")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Table implements Comparable<Table> {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;

    private String tableName;

    private String qrCode;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Guest> guests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Restaurant restaurant;

    public void addRestaurant(Restaurant restaurant) {
        restaurant.addTable(this);
    }

    public void addGuest(Guest guest) {
        if (guests == null) guests = new HashSet<>();
        guests.add(guest);
        guest.setTable(this);
    }

    @Override
    public int compareTo(Table o) {
        if (getId() == null || o.getId() == null) {
            return 0;
        }
        return getId().compareTo(o.getId());
    }
}
