package be.intecbrussel.DietPlanner.service.meal;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.MealsDataBase;
import be.intecbrussel.DietPlanner.model.ProductMeal;
import be.intecbrussel.DietPlanner.model.UserEntity;
import be.intecbrussel.DietPlanner.repository.MealDatabaseRepository;
import be.intecbrussel.DietPlanner.repository.MealRepository;
import be.intecbrussel.DietPlanner.repository.ProductRepository;
import be.intecbrussel.DietPlanner.repository.UserRepository;
import be.intecbrussel.DietPlanner.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final MealDatabaseRepository mealDatabaseRepository;
    private final UserRepository userRepository;

    @Override
    public void saveMeal(Meal meal) {
        if (meal.getName().isEmpty() || meal.getDescription().isEmpty() || meal.getProductMeals().isEmpty()) {
            log.error("exception thrown in saveMeal");
            throw new IllegalArgumentException("please fill in all boxes");
        }
        //meal.setAteAt(new Date(System.currentTimeMillis()));
        MealsDataBase addMealToDatabase = mealDatabaseRepository.findById(1L).orElseThrow(() -> new NullPointerException("no database found"));
        log.info("saving new meal: {} to database", meal.getName());
        meal.setTotalCalories(calculateCalories(meal));
        mealRepository.save(meal);
        addMealToDatabase.getMeals().add(meal);
        mealDatabaseRepository.save(addMealToDatabase);

    }

    public void addMealToUser(String username, Long mealId){
        if(username.isEmpty() || mealId == null){
            throw new NullPointerException("arguments cannot be empty");
        }
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new NullPointerException("Meal not found"));
        log.info("saving new meal: {} to user: {}", meal.getName(), username);
        meal.getUsers().add(user);
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
