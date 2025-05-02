package com.example.fitnessmeal.FitnessMealService.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "mealplans")
@Data
public class MealPlan {
    @Id
    private String id;
    private String userId;
    private LocalDate date;
    private List<Meal> meals;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;

    @Data
    public static class Meal {
        private String name;
        private MealType type;
        private List<String> ingredients;
        private double calories;
        private double protein;
        private double carbs;
        private double fat;
        private String recipe;
    }

    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }
}