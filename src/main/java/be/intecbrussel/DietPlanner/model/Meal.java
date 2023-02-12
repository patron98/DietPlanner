package be.intecbrussel.DietPlanner.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor
public class Meal {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @Lob
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductMeal> productMeals;


    private int totalCalories;

    private Boolean hasEaten;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ate_at", nullable = false, updatable = false)
    @CreatedDate
    private Date ateAt;

    public Meal(String name, String description, List<ProductMeal> productMeals, int totalCalories, Boolean hasEaten, Date ateAt) {
        this.name = name;
        this.description = description;
        this.productMeals = productMeals;
        this.totalCalories = totalCalories;
        this.hasEaten = hasEaten;
        this.ateAt = ateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return totalCalories == meal.totalCalories && Objects.equals(id, meal.id) && Objects.equals(name, meal.name) && Objects.equals(description, meal.description) && Objects.equals(productMeals, meal.productMeals) && Objects.equals(hasEaten, meal.hasEaten) && Objects.equals(ateAt, meal.ateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, productMeals, totalCalories, hasEaten, ateAt);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
