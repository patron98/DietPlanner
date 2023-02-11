package be.intecbrussel.DietPlanner.repository;

import be.intecbrussel.DietPlanner.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

}
