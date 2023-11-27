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

    @Column(length=100000)
    private byte[] qrCodeBlob;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Guest> guests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Restaurant restaurant;

    @OneToMany
    @JsonManagedReference
    private Set<Order> orders;

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

    @PreRemove
    private void removeAssociations() {
        this.restaurant.getTables().remove(this);
        for(Guest guest : this.guests) {
           guest.setTable(null);
        }
    }
}
