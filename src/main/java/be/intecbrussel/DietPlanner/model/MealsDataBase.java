package be.intecbrussel.DietPlanner.model;

import be.intecbrussel.DietPlanner.model.response.UserResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meals_database")
@Getter
@Setter
@NoArgsConstructor
public class MealsDataBase {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    private List<Meal> meals;


}
