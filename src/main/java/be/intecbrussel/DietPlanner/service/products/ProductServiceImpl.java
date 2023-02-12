package be.intecbrussel.DietPlanner.service.products;

import be.intecbrussel.DietPlanner.model.Product;
import be.intecbrussel.DietPlanner.model.ProductMeal;
import be.intecbrussel.DietPlanner.model.request.ProductRequest;
import be.intecbrussel.DietPlanner.repository.ProductMealRepository;
import be.intecbrussel.DietPlanner.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductMealRepository productMealRepository;


    @Override
    public void saveProduct(ProductRequest request) {
        if (request == null){
            throw new IllegalArgumentException("request cannot be empty");
        }
        boolean checkProduct = productRepository.findProductByName(request.getName()).isPresent();
        if(checkProduct){
            throw new IllegalArgumentException("product already exist");
        }
        log.info("Saving product {} to database", request.getName());
        Product product = new Product();
        product.setName(request.getName());
        product.setCalories(request.getCalories());
        product.setFat(request.getFat());
        product.setCarbs(request.getCarbs());
        product.setProteins(request.getProteins());
        product.setSugar(request.getSugar());
        product.setWeight(request.getWeight());

        productRepository.save(product);
        saveProductMeal(product.getName());
    }

    @Override
    public void saveProductMeal(String name) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("name cannot be empty");
        }
        boolean checkProduct = productMealRepository.findProductMealByName(name).isPresent();
        if(checkProduct){
            throw new IllegalArgumentException("product already exist");
        }
        Product foundProduct = productRepository.findProductByName(name)
                .orElseThrow(() -> new RuntimeException("product not found"));
        log.info("saving new productMeal: {}", foundProduct.getName());
        ProductMeal productMeal = new ProductMeal();
        productMeal.setName(foundProduct.getName());
        productMeal.setProduct(foundProduct);
        productMealRepository.save(productMeal);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id).map(product -> {
            product.setName(request.getName());
            product.setCalories(request.getCalories());
            product.setFat(request.getFat());
            product.setCarbs(request.getCarbs());
            product.setProteins(request.getProteins());
            product.setSugar(request.getSugar());
            product.setWeight(request.getWeight());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("product with id: " +
                id + " not found"));
    }

    @Override
    public Product getProductByName(String name) {
        if(name == null){
            throw new IllegalArgumentException("name cannot be empty");
        }
        log.info("fetching product: {}", name);
        return productRepository.findProductByName(name)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));
    }
    @Override
    public ProductMeal getProductMealByName(String name) {
        if(name == null){
            throw new IllegalArgumentException("name cannot be empty");
        }
        log.info("fetching product: {}", name);
        return productMealRepository.findProductMealByName(name)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductMeal> getAllProductMeals() {
        return productMealRepository.findAll();
    }

    @Override
    public void deleteProductById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be empty");
        }
        productRepository.deleteById(id);
    }
}
