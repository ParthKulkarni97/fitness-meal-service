package com.example.fitnessmeal.FitnessMealService.Repo;

import com.example.fitnessmeal.FitnessMealService.Model.MealPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealPlanRepository extends MongoRepository<MealPlan, String> {
    //List<MealPlan> findByUserId(String userId);
    void deleteByUserId(String userId);
    MealPlan.Meal findMealPlanByUserId(String userId);
}