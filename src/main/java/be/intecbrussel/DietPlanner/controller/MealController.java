package be.intecbrussel.DietPlanner.controller;

import be.intecbrussel.DietPlanner.model.Meal;
import be.intecbrussel.DietPlanner.model.ProductMeal;
import be.intecbrussel.DietPlanner.service.database.MealDataBaseService;
import be.intecbrussel.DietPlanner.service.meal.MealService;
import be.intecbrussel.DietPlanner.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    private final ProductService productService;
    private final MealDataBaseService mealDataBaseService;

    @GetMapping("/meals")
    public String getMeals(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/" + request.getUserPrincipal().getName() + "/meals";
        }
        return "index";
    }

    @GetMapping("/{username}/meals")
    public String getMealsLoggedIn(@PathVariable String username, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication.getName().equals(username)) {
            model.addAttribute("databaseMeals", mealDataBaseService.getAllDatabaseMeals());
            return "meals";
        }
        return "index";
    }

    @GetMapping("/meals/new-meal")
    public String newMealPage(HttpServletRequest request, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/" + request.getUserPrincipal().getName() + "/meals/new-meal";
        }
        return "index";
    }

    @GetMapping("/{username}/meals/new-meal")
    public String goToNewMealPage(@PathVariable String username, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication.getName().equals(username)) {
            model.addAttribute("meal", new Meal());
            model.addAttribute("products", productService.getAllProductMeals());
            return "createMeal";
        }
        return "index";
    }

    @PostMapping("/meals/new-meal")
    public String saveNewMealPage(@ModelAttribute Meal meal, Model model) {
        model.addAttribute("meal", new Meal());
        mealService.saveMeal(meal);
        return "redirect:/meals";

    }

    @PostMapping("/{username}/meals")
    public String addMealToUser(@PathVariable String username, @RequestParam("mealId") Long mealId){
        mealService.addMealToUser(username, mealId);
        return "redirect:/" + username + "/overview";
    }


}
