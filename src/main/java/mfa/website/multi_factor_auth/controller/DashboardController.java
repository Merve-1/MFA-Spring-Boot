package mfa.website.multi_factor_auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, HttpServletRequest request, Model model) {

        // Check if the authenticated user is a regular UserDetails-based user
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            model.addAttribute("username", userDetails.getUsername()); // Add username to the model
            model.addAttribute("authorities", userDetails.getAuthorities()); // Add user roles/authorities

            // Check if the authenticated user is an OAuth2-based user (e.g., Google, Facebook login)
        } else if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            model.addAttribute("username", oauth2User.getAttribute("name")); // Add OAuth2 user's name
            model.addAttribute("email", oauth2User.getAttribute("email")); // Add OAuth2 user's email
            model.addAttribute("authorities", oauth2User.getAuthorities()); // Add user roles/authorities
        }

        // Retrieve and add CSRF token to the model for form submissions
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            model.addAttribute("csrf", csrf);
        }

        return "pages/dashboard"; // Returns the dashboard page template
    }
}
