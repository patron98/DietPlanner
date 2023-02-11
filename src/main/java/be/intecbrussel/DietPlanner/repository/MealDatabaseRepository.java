package be.intecbrussel.DietPlanner.repository;

import be.intecbrussel.DietPlanner.model.MealsDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealDatabaseRepository extends JpaRepository<MealsDataBase, Long> {

}
