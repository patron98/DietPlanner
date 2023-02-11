package be.intecbrussel.DietPlanner.service.products;

import be.intecbrussel.DietPlanner.model.Product;
import be.intecbrussel.DietPlanner.model.ProductMeal;
import be.intecbrussel.DietPlanner.model.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductRequest product);

    void saveProductMeal(String productName);

    Product updateProduct(Long id, ProductRequest request);

    Product getProductByName(String name);

    List<Product> getAllProducts();

    void deleteProductById(Long id);
}
