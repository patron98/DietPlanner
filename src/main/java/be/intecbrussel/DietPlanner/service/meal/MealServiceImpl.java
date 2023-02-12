package be.intecbrussel.DietPlanner.service.meal;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.ProductMeal;
import be.intecbrussel.DietPlanner.repository.MealRepository;
import be.intecbrussel.DietPlanner.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final ProductRepository productRepository;

    @Override
    public void saveMeal(Meal meal) {
        if (meal.getName().isEmpty() || meal.getDescription().isEmpty() || meal.getProductMeals().isEmpty()) {
            throw new IllegalArgumentException("please fill in all boxes");
        }
        log.info("saving new meal: {} to database", meal.getName());
        meal.setAteAt(new Date(System.currentTimeMillis()));
        meal.setTotalCalories(calculateCalories(meal));
        mealRepository.save(meal);

    }

    @Override
    public int calculateCalories(Meal meal) {
        if(meal == null){
            throw new IllegalArgumentException("meals cannot be empty");
        }
        return meal.getProductMeals().stream()
                .mapToInt(productMeal -> productRepository.findProductByName(productMeal.getName()).get().getCalories())
                .sum();
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
