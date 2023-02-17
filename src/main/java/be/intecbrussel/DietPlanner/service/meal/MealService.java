package be.intecbrussel.DietPlanner.service.meal;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.ProductMeal;

import java.util.List;

public interface MealService {

    void saveMeal(Meal meal);
    void addMealToUser(String username, Long mealId);

    public List<Meal> getAllMealsFromUser();

    int calculateCaloriesFromAllMeals(List<Meal> meals);

    int calculateCalories(Meal meal);

    List<Meal> getAllMeals();
}
