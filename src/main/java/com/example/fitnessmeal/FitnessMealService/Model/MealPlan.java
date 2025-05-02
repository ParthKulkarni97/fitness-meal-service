package com.example.fitnessmeal.FitnessMealService.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "mealplans")
@Data
@Getter
@Setter
public class MealPlan {

    @Id
    private String id;
    private String userId;
    private List<String> meals;

    public MealPlan() {}

    public MealPlan(String userId, List<String> meals) {
        this.userId = userId;
        this.meals = meals;
    }
}
