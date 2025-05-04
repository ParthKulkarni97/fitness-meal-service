package com.example.fitnessmeal.FitnessMealService.Controller;

import com.example.fitnessmeal.FitnessMealService.Model.MealPlan;
import com.example.fitnessmeal.FitnessMealService.Service.MealPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mealplans")
@Slf4j
public class MealPlanController {

    private final MealPlanService mealPlanService;

    @Autowired
    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping("/{userId}")
    public MealPlan generateMealPlan(
            @PathVariable String userId) {
        log.info("Generating meal plan for user: {}", userId);
        return mealPlanService.generateMealPlan(userId);
    }

    @GetMapping("/{userId}")
    public MealPlan.Meal getUserMealPlans(
            @PathVariable String userId) {
        return mealPlanService.getMealPlans(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteMealPlan(
            @PathVariable String userId) {
        mealPlanService.deleteMealPlan(userId);
        return ResponseEntity.noContent().build();
    }
}