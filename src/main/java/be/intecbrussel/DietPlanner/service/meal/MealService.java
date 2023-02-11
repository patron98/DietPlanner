package be.intecbrussel.DietPlanner.service.meal;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.ProductMeal;

import java.util.List;

public interface MealService {

    void saveMeal(String name, String description, List<ProductMeal> productMeals);

    void calculateCalories(Meal meal);

    List<Meal> getAllMeals();
}
