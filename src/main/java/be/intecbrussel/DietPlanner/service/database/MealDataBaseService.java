package be.intecbrussel.DietPlanner.service.database;

import be.intecbrussel.DietPlanner.model.MealsDataBase;

import java.util.List;

public interface MealDataBaseService {
    void createDatabase();
    void deleteDatabase();

    List<MealsDataBase> getAllDatabaseMeals();
}
