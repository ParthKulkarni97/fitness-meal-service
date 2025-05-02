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
    public ResponseEntity<MealPlan> generateMealPlan(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Generating meal plan for user: {} for date: {}", userId, date);
        MealPlan mealPlan = mealPlanService.generateMealPlan(userId, date);
        return ResponseEntity.ok(mealPlan);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MealPlan>> getUserMealPlans(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<MealPlan> mealPlans = mealPlanService.getMealPlansByDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(mealPlans);
    }

    @DeleteMapping("/{userId}/{date}")
    public ResponseEntity<Void> deleteMealPlan(
            @PathVariable String userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        mealPlanService.deleteMealPlan(userId, date);
        return ResponseEntity.noContent().build();
    }
}