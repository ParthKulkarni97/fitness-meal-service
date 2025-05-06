package com.example.fitnessmeal.FitnessMealService.Controller;

import com.example.fitnessmeal.FitnessMealService.Model.MealPlan;
import com.example.fitnessmeal.FitnessMealService.Service.MealPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MealPlan> generateMealPlan(@PathVariable String userId) {
        log.info("Generating meal plan for user: {}", userId);
        MealPlan mealPlan = mealPlanService.generateMealPlan(userId);
        return ResponseEntity.ok(mealPlan);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MealPlan>> getUserMealPlans(@PathVariable String userId) {
        List<MealPlan> mealPlans = mealPlanService.getMealPlans(userId);
        return ResponseEntity.ok(mealPlans);
    }

    @DeleteMapping("/{userId}/{mealPlanId}")
    public ResponseEntity<Void> deleteMealPlan(
            @PathVariable String userId,
            @PathVariable String mealPlanId) {
        mealPlanService.deleteMealPlan(userId, mealPlanId);
        return ResponseEntity.noContent().build();
    }
}