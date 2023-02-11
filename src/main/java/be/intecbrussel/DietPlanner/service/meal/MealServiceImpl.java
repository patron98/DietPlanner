package be.intecbrussel.DietPlanner.service.meal;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.ProductMeal;
import be.intecbrussel.DietPlanner.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    @Override
    public void saveMeal(String name, String description, List<ProductMeal> productMeals) {
        if(name.isEmpty() || description.isEmpty() || productMeals.isEmpty()){
            throw new IllegalArgumentException("please fill in all boxes");
        }
        log.info("saving new meal: {} to database", name);
        Meal newMeal = new Meal();
        newMeal.setName(name);
        newMeal.setDescription(description);
        newMeal.setProductMeals(productMeals);
        mealRepository.save(newMeal);
    }

    @Override
    public void calculateCalories(Meal meal) {
        if(meal == null){
            throw new IllegalArgumentException("meals cannot be empty");
        }
        int calories = meal.getProductMeals().stream()
                .mapToInt(productMeal -> productMeal.getProduct().getCalories())
                .sum();
        meal.setTotalCalories(calories);
        mealRepository.save(meal);
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
