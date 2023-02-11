package be.intecbrussel.DietPlanner.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(max = 20)
    private int calories;

    @NotEmpty
    @Size(max = 20)
    private double carbs;

    @NotEmpty
    @Size(max = 20)
    private double fat;

    @NotEmpty
    @Size(max = 20)
    private double proteins;

    @NotEmpty
    @Size(max = 20)
    private double sugar;

    @NotEmpty
    @Size(max = 20)
    private double weight;

    @NotEmpty
    @Size(max = 20)
    private int amount;

    public Product(String name, int calories, float carbs, float fat, float proteins, float weight, int amount) {
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.proteins = proteins;
        this.weight = weight;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return calories == product.calories && Double.compare(product.carbs, carbs) == 0 && Double.compare(product.fat, fat) == 0 && Double.compare(product.proteins, proteins) == 0 && Double.compare(product.sugar, sugar) == 0 && Double.compare(product.weight, weight) == 0 && amount == product.amount && Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, calories, carbs, fat, proteins, sugar, weight, amount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", carbs=" + carbs +
                ", fat=" + fat +
                ", proteins=" + proteins +
                '}';
    }
}
