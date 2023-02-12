package be.intecbrussel.DietPlanner.service.meal;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.ProductMeal;

import java.util.List;

public interface MealService {

    void saveMeal(Meal meal);

    int calculateCalories(Meal meal);

    List<Meal> getAllMeals();
}
