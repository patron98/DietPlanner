package be.intecbrussel.DietPlanner.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private int calories;
    private double carbs;
    private double fat;
    private double proteins;
    private double sugar;
    private double weight;


}
