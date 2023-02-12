package be.intecbrussel.DietPlanner.controller;

import be.intecbrussel.DietPlanner.model.Address;
import be.intecbrussel.DietPlanner.model.UserEntity;
import be.intecbrussel.DietPlanner.model.request.ProductRequest;
import be.intecbrussel.DietPlanner.model.request.UserRequest;
import be.intecbrussel.DietPlanner.service.meal.MealService;
import be.intecbrussel.DietPlanner.service.products.ProductService;
import be.intecbrussel.DietPlanner.service.user.UserService;
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
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final ProductService productService;
    private final MealService mealService;
    private final UserService userService;

    @GetMapping("/home")
    public String getIndexPage(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/" + request.getUserPrincipal().getName() + "/dashboard";
        }
        return "index";
    }

    @GetMapping("/dashboard")
    public String getDashboard(HttpServletRequest request, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("username", userService.getUserByUserName(authentication.getName()).getUsername());
            return "redirect:/" + request.getUserPrincipal().getName() + "/dashboard";
        }

        return "index";
    }

    @GetMapping("/{username}/dashboard")
    public String getLoggedInDashboard(@PathVariable String username, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (!(authentication instanceof AnonymousAuthenticationToken)&& authentication.getName().equals(username)) {
            model.addAttribute("userDetails", principal);
            return "dashboard";
        }

        // if it is not authenticated, then go to the index...
        // other things ...
        return "index";
    }

    @GetMapping("/address")
    public String goToAddressPage(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/" + request.getUserPrincipal().getName() + "/address";
        }

        return "index";
    }

    @GetMapping("/{username}/address")
    public String goToAddressPageLoggedIn(@PathVariable String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)&& authentication.getName().equals(username)) {

            return "address";
        }
        return "index";
    }

    @PostMapping("/address")
    public String getProductPageLoggedIn( Model model, Address address){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("address", new Address());
        userService.addAddressToUser(username, address);
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String goToLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        return "redirect:/" + authentication.getName() +"/dashboard";
    }

/*
    @PostMapping("/login")
    public String Login(LoginRequest loginRequest, HttpSession httpSession){
        if(userService.login(loginRequest)){
            httpSession.setAttribute("loginUserName", loginRequest.getUsername());
            return "redirect:/{username}/dashboard";
        }
        return "redirect:/login";
    }

 */




    @GetMapping("/register")
    public String returnRegisterPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register";
        }
        return "redirect:/" + authentication.getName() +"/dashboard";
    }

    @PostMapping("/register")
    public String registerUser(UserRequest request, Model model){
        model.addAttribute("request", new UserRequest());
        userService.saveUser(request);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String goToProfilePage(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/" + request.getUserPrincipal().getName() + "/profile";
        }

        return "index";
    }
    @GetMapping("/{username}/profile")
    public String goToProfilePageLoggedIn(@PathVariable String username, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.getUserByUserName(authentication.getName());
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication.getName().equals(username)) {
            model.addAttribute("user", user);
            return "profile";
        }

        return "index";
    }
}
