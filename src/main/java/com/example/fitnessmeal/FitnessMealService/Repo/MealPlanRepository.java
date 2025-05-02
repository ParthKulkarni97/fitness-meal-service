package com.example.fitnessmeal.FitnessMealService.Repo;

import com.example.fitnessmeal.FitnessMealService.Model.MealPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface MealPlanRepository extends MongoRepository<MealPlan, String> {
    List<MealPlan> findByUserId(String userId);
    List<MealPlan> findByUserIdAndDateBetween(String userId, LocalDate startDate, LocalDate endDate);
    void deleteByUserIdAndDate(String userId, LocalDate date);
}