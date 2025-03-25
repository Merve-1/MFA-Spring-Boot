package mfa.website.multi_factor_auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Marks this class as a Spring MVC Controller
public class LoginController {

    @GetMapping("/login") // Handles GET requests for the login page
    public String login(HttpServletRequest request, Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        return "pages/login"; // Returns the login page template
    }

    @GetMapping("/")
    public String home() {
        return "pages/home"; // Returns the home page template
    }

    @GetMapping("/register")
    public String register() {
        return "pages/register"; // Returns the registration page template
    }
}
