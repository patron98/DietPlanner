package be.intecbrussel.DietPlanner.controller;

import be.intecbrussel.DietPlanner.model.Address;
import be.intecbrussel.DietPlanner.model.UserEntity;
import be.intecbrussel.DietPlanner.model.request.LoginRequest;
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
    public String getIndexPage(){

        return "index";
    }

    @GetMapping("/dashboard")
    public String getDashboard(HttpServletRequest request, Model model){
        String username = request.getUserPrincipal().getName();
        return "dashboard";
    }

    @GetMapping("/{username}/dashboard")
    public String getLoggedInDashboard(@PathVariable String username, Model model){
        model.addAttribute("username", userService.getUserByUserName(username).getUsername());
        return "dashboardLoggedIn";
    }


    @GetMapping("/{username}/address")
    public String goToAddressPage(@PathVariable String username, Model model){
        model.addAttribute("username", userService.getUserByUserName(username).getUsername());
        return "address";
    }

    @PostMapping("/{username}/address")
    public String addAddressToUser(@PathVariable String username, Address address, Model model){
        UserEntity user = userService.getUserByUserName(username);
        model.addAttribute("address", new Address());
        userService.addAddressToUser(username, address);
        return "redirect:/dashboardLoggedIn";

    }

    @GetMapping("/login")
    public String goToLoginPage(HttpSession httpSession, HttpServletRequest httpServletRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/login";
        }
        return "redirect:/";
    }


    @PostMapping("/login")
    public String Login(LoginRequest loginRequest, HttpSession httpSession){
        if(userService.login(loginRequest)){
            httpSession.setAttribute("loginUserName", loginRequest.getUsername());
            return "redirect:/";
        }
        return "redirect:/login";
    }


    @GetMapping("/register")
    public String returnRegisterPage(HttpSession httpSession){
        if(httpSession.getAttribute("loginUserName") != null){
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserRequest request, Model model){
        model.addAttribute("request", new UserRequest());
        userService.saveUser(request);
        return "redirect:/login";
    }
}
