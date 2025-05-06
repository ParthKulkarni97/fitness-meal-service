package com.example.fitnessmeal.FitnessMealService.Service;

import com.example.fitnessmeal.FitnessMealService.Model.MealPlan;
import com.example.fitnessmeal.FitnessMealService.Model.User;
import com.example.fitnessmeal.FitnessMealService.Repo.MealPlanRepository;
import com.example.fitnessmeal.FitnessMealService.Repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@Transactional
public class MealPlanService {

    private final UserRepository userRepository;
    private final MealPlanRepository mealPlanRepository;

    @Autowired
    public MealPlanService(UserRepository userRepository, MealPlanRepository mealPlanRepository) {
        this.userRepository = userRepository;
        this.mealPlanRepository = mealPlanRepository;
    }

    public MealPlan generateMealPlan(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        MealPlan mealPlan = new MealPlan();
        mealPlan.setUserId(userId);
        mealPlan.setMeals(generateMealsBasedOnPreferences(user));

        return mealPlanRepository.save(mealPlan);
    }

    public List<MealPlan> getMealPlans(String userId) {
        return mealPlanRepository.findByUserId(userId);
    }

    public void deleteMealPlan(String userId, String mealPlanId) {
        MealPlan mealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new RuntimeException("Meal plan not found"));

        if (!mealPlan.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to meal plan");
        }

        mealPlanRepository.deleteById(mealPlanId);
    }

    private List<MealPlan.Meal> generateMealsBasedOnPreferences(User user) {
        List<MealPlan.Meal> meals = new ArrayList<>();

        switch (user.getPreferredDiet()) {
            case MUSCLE_GAIN -> generateHighProteinMeals(meals);
            case CALORIE_DEFICIT -> generateLowCalorieMeals(meals);
            case STRENGTH -> generateBalancedMeals(meals);
            default -> generateDefaultMeals(meals);
        }

        return meals;
    }

    private void generateHighProteinMeals(List<MealPlan.Meal> meals) {
        MealPlan.Meal breakfast = new MealPlan.Meal();
        breakfast.setName("Protein Pancakes");
        breakfast.setType(MealPlan.MealType.BREAKFAST);
        breakfast.setCalories(450);
        breakfast.setProtein(35);
        breakfast.setCarbs(40);
        breakfast.setFat(15);
        breakfast.setIngredients(Arrays.asList("Protein Powder", "Eggs", "Oats", "Greek Yogurt"));
        breakfast.setRecipe("1. Mix ingredients\n2. Cook on griddle");
        meals.add(breakfast);

        // Add more meals similarly
        // Add lunch
        MealPlan.Meal lunch = new MealPlan.Meal();
        lunch.setName("Chicken Rice Bowl");
        lunch.setType(MealPlan.MealType.LUNCH);
        lunch.setCalories(600);
        lunch.setProtein(45);
        lunch.setCarbs(65);
        lunch.setFat(20);
        lunch.setIngredients(Arrays.asList("Chicken Breast", "Brown Rice", "Broccoli", "Olive Oil"));
        lunch.setRecipe("1. Cook rice\n2. Grill chicken\n3. Steam broccoli\n4. Combine all ingredients");
        meals.add(lunch);

        // Add dinner
        MealPlan.Meal dinner = new MealPlan.Meal();
        dinner.setName("Salmon with Sweet Potato");
        dinner.setType(MealPlan.MealType.DINNER);
        dinner.setCalories(550);
        dinner.setProtein(40);
        dinner.setCarbs(45);
        dinner.setFat(25);
        dinner.setIngredients(Arrays.asList("Salmon Fillet", "Sweet Potato", "Asparagus", "Butter"));
        dinner.setRecipe("1. Bake salmon\n2. Roast sweet potato\n3. Grill asparagus");
        meals.add(dinner);
    }

    // Implement other meal generation methods similarly
    private void generateLowCalorieMeals(List<MealPlan.Meal> meals) {
        // Implementation for low calorie meals
        MealPlan.Meal breakfast = new MealPlan.Meal();
        breakfast.setName("Oatmeal with Berries");
        breakfast.setType(MealPlan.MealType.BREAKFAST);
        breakfast.setCalories(300);
        breakfast.setProtein(10);
        breakfast.setCarbs(45);
        breakfast.setFat(8);
        breakfast.setIngredients(List.of("Oats", "Mixed Berries", "Almond Milk", "Chia Seeds"));
        breakfast.setRecipe("1. Cook oats with almond milk\n2. Top with berries and chia seeds");

        MealPlan.Meal lunch = new MealPlan.Meal();
        lunch.setName("Grilled Chicken Salad");
        lunch.setType(MealPlan.MealType.LUNCH);
        lunch.setCalories(350);
        lunch.setProtein(35);
        lunch.setCarbs(15);
        lunch.setFat(12);
        lunch.setIngredients(List.of("Chicken Breast", "Mixed Greens", "Cherry Tomatoes", "Balsamic Vinaigrette"));
        lunch.setRecipe("1. Grill chicken breast\n2. Mix with greens and tomatoes\n3. Dress with vinaigrette");

        meals.add(breakfast);
        meals.add(lunch);
        // Add more meals as needed
    }

    private void generateBalancedMeals(List<MealPlan.Meal> meals) {
        MealPlan.Meal dinner = new MealPlan.Meal();
        dinner.setName("Salmon with Quinoa");
        dinner.setType(MealPlan.MealType.DINNER);
        dinner.setCalories(500);
        dinner.setProtein(30);
        dinner.setCarbs(45);
        dinner.setFat(20);
        dinner.setIngredients(List.of("Salmon", "Quinoa", "Vegetables", "Olive Oil"));
        dinner.setRecipe("1. Bake salmon\n2. Cook quinoa\n3. Steam vegetables");

        meals.add(dinner);
        // Add more balanced meals
    }

    private void generateDefaultMeals(List<MealPlan.Meal> meals) {
        // Generate standard balanced meals
        generateBalancedMeals(meals);
    }
}