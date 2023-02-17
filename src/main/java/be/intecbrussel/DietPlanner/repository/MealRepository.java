package be.intecbrussel.DietPlanner.repository;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findMealsByUsers_Id(Long userId);
}
