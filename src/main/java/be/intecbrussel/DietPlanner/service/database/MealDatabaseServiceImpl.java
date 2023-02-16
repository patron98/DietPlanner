package be.intecbrussel.DietPlanner.service.database;

import be.intecbrussel.DietPlanner.model.MealsDataBase;
import be.intecbrussel.DietPlanner.repository.MealDatabaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MealDatabaseServiceImpl implements MealDataBaseService{

    private final MealDatabaseRepository mealDatabaseRepository;

    @Override
    public void createDatabase() {
        MealsDataBase mealsDataBase = new MealsDataBase();
        mealDatabaseRepository.save(mealsDataBase);
    }

    @Override
    public void deleteDatabase() {

    }

    @Override
    public List<MealsDataBase> getAllDatabaseMeals(){
        return mealDatabaseRepository.findAll();
    }
}
