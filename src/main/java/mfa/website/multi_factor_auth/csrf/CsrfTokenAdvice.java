package mfa.website.multi_factor_auth.csrf;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * This class provides global CSRF token handling for all controllers in the application.
 * It automatically makes the CSRF token available in the model, allowing templates to use it.
 */
@ControllerAdvice // This annotation makes the class apply to all controllers globally.
public class CsrfTokenAdvice {


    @ModelAttribute("csrf") // Adds this method's return value to the model under the name "csrf".
    public CsrfToken csrf(HttpServletRequest request) {
        // Retrieves the CSRF token stored in the request attributes by Spring Security.
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }


    @ModelAttribute("csrfHiddenInput") // Adds this method's return value to the model under the name "csrfHiddenInput".
    public CsrfHiddenInput csrfHiddenInput(HttpServletRequest request) {
        // Creates a new CsrfHiddenInput instance, passing the CSRF token.
        return new CsrfHiddenInput(csrf(request));
    }
}
