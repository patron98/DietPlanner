package be.intecbrussel.DietPlanner.controller;

import be.intecbrussel.DietPlanner.model.Product;
import be.intecbrussel.DietPlanner.model.request.ProductRequest;
import be.intecbrussel.DietPlanner.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String goToProductPage(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/" + request.getUserPrincipal().getName() + "/products";
        }

        return "index";
    }

    @GetMapping("/{username}/products")
    public String getProductPageLoggedIn(@PathVariable String username, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)&& authentication.getName().equals(username)) {
            model.addAttribute("products", productService.getAllProducts());
            return "products";
        }
        return "index";
    }
}
