package be.intecbrussel.DietPlanner.repository;

import be.intecbrussel.DietPlanner.model.ProductMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMealRepository extends JpaRepository<ProductMeal, Long> {

    Optional<ProductMeal> findProductMealByName(String name);
}
