package be.intecbrussel.DietPlanner.controller;

import be.intecbrussel.DietPlanner.model.Product;
import be.intecbrussel.DietPlanner.model.request.ProductRequest;
import be.intecbrussel.DietPlanner.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public String getProductPageLoggedIn( Model model, ProductRequest request){
        model.addAttribute("product", new ProductRequest());
        productService.saveProduct(request);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getProductPageLoggedIn( Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "productsLoggedIn";
    }
}
