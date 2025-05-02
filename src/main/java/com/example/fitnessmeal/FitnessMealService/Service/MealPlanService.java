package com.example.fitnessmeal.FitnessMealService.Service;

import com.example.fitnessmeal.FitnessMealService.Model.MealPlan;
import com.example.fitnessmeal.FitnessMealService.Model.User;
import com.example.fitnessmeal.FitnessMealService.Repo.MealPlanRepository;
import com.example.fitnessmeal.FitnessMealService.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MealPlanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;

    public MealPlan generateMealPlanForUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        User user = optionalUser.get();

        List<String> vegMeals = Arrays.asList("Paneer Butter Masala", "Chole Bhature", "Veg Pulao");
        List<String> nonVegMeals = Arrays.asList("Chicken Biryani", "Grilled Fish", "Mutton Korma");

        List<String> meals;
        if (user.getPreferredDiet() == User.DietPreference.Veg) {
            meals = vegMeals;
        } else {
            meals = nonVegMeals;
        }

        MealPlan mealPlan = new MealPlan(user.getId(), meals);
        return mealPlanRepository.save(mealPlan);
    }
}
