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

    @NotEmpty
    private int quantity;

    public ProductMeal(String name, Product product, int quantity) {
        this.name = name;
        this.product = product;
        this.quantity = quantity;
    }

    /*
    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

     */



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductMeal that = (ProductMeal) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(product, that.product);
    }

    @Override
    public String toString() {
        return "ProductMeal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, product, quantity);
    }
}
