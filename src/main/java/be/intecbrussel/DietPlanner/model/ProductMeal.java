package be.intecbrussel.DietPlanner.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_meals")
@Getter
@Setter
@NoArgsConstructor
public class ProductMeal {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "meal_products",
            joinColumns = { @JoinColumn(name = "product_meal_id") },
            inverseJoinColumns = { @JoinColumn(name = "meal_id") })
    private List<Meal> meals;



    public ProductMeal(String name) {
        this.name = name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductMeal that = (ProductMeal) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, product);
    }

    @Override
    public String toString() {
        return "ProductMeal{" +
                "name='" + name + '\'' +
                ", product=" + product +
                '}';
    }
}
