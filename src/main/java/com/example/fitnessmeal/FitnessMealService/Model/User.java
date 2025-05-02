package com.example.fitnessmeal.FitnessMealService.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Getter
@Setter
public class User {

    @Id
    private String id;
    private String name;
    private DietPreference preferredDiet;

    public enum DietPreference {
        Veg,
        NonVeg
    }

    public User() {
    }

    public User(String id, String name, DietPreference preferredDiet) {
        this.id = id;
        this.name = name;
        this.preferredDiet = preferredDiet;
    }

}

