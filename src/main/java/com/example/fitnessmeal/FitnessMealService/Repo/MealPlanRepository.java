package com.example.fitnessmeal.FitnessMealService.Repo;

import com.example.fitnessmeal.FitnessMealService.Model.MealPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MealPlanRepository extends MongoRepository<MealPlan, String> {
    void deleteByUserId(String userId);
    MealPlan.Meal findMealPlanByUserId(String userId);
    List<MealPlan> findByUserId(String userId);
}