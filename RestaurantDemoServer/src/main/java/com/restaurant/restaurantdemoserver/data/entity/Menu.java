package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "menus")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Drink> alcoholicDrinks;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Drink> nonAlcoholicDrinks;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Food> starters;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Food> soups;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Food> mainCourses;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Food> desserts;

    public void addStarters(Food food){
        if(starters == null) starters = new HashSet<>();
        food.setMenu(this);
        starters.add(food);
    }
    public void addSoups(Food food){
        if(soups == null) soups = new HashSet<>();
        food.setMenu(this);
        soups.add(food);
    }
    public void addMainCourses(Food food){
        if(mainCourses == null) mainCourses = new HashSet<>();
        food.setMenu(this);
        mainCourses.add(food);
    }
    public void addDesserts(Food food){
        if(desserts == null) desserts = new HashSet<>();
        food.setMenu(this);
        desserts.add(food);
    }
    public void addNonAlcoholicDrink(Drink drink){
        if(nonAlcoholicDrinks == null) nonAlcoholicDrinks = new HashSet<>();
        drink.setMenu(this);
        nonAlcoholicDrinks.add(drink);
    }

    public void addAlcoholicDrink(Drink drink){
        if(alcoholicDrinks == null) alcoholicDrinks = new HashSet<>();
        drink.setMenu(this);
        alcoholicDrinks.add(drink);
    }
}
