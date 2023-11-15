package com.restaurant.restaurantdemoserver.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurant.restaurantdemoserver.data.helper.MenuItemType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "foods")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Food {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;

    private String name;

    private Double price;

    private Integer rating;

    @Enumerated(EnumType.STRING)
    private MenuItemType menuItemType;

    @ManyToMany()
    @JsonManagedReference
    private Set<FoodAllergy> foodAllergies;

    @Column(length=100000)
    private byte[] pictureBlob;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Guest guest;

    public void addFoodAllergy(FoodAllergy foodAllergy){
        if(foodAllergies == null) foodAllergies = new HashSet<>();

    }

    @PreRemove
    private void removeAssociations() {
       this.menu.getMainCourses().remove(this);
       this.menu.getDesserts().remove(this);
       this.menu.getSoups().remove(this);
       this.menu.getStarters().remove(this);
       this.menu = null;
       this.guest = null;
       for(FoodAllergy foodAllergy : this.foodAllergies) {
           foodAllergy.getFoods().remove(this);
       }
    }
}
