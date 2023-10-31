package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "guests")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Guest {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Table table;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Food> orderedFoods;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Drink> orderedDrinks;

    public void orderDrink(Drink drink){
        if(orderedDrinks == null) orderedDrinks = new ArrayList<>();
        orderedDrinks.add(drink);
    }
    public void orderFood(Food food){
        if(orderedFoods == null) orderedFoods = new ArrayList<>();
        orderedFoods.add(food);
    }

   public void addTable(Table table){
        table.addGuest(this);
   }
}
