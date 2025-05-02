package com.example.fitnessmeal.FitnessMealService.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private DietPreference preferredDiet;
    private int age;
    private double weight;
    private double height;
    private ActivityLevel activityLevel;
    private FitnessGoal fitnessGoal;

    public enum DietPreference {
        CALORIE_DEFICIT,
        MUSCLE_GAIN,
        STRENGTH,
        MAINTENANCE
    }

    public enum ActivityLevel {
        SEDENTARY,
        LIGHTLY_ACTIVE,
        MODERATELY_ACTIVE,
        VERY_ACTIVE,
        EXTREMELY_ACTIVE
    }

    public enum FitnessGoal {
        WEIGHT_LOSS,
        MUSCLE_GAIN,
        MAINTENANCE,
        GENERAL_FITNESS
    }
}