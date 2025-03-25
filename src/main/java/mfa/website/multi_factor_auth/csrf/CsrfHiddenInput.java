package mfa.website.multi_factor_auth.csrf;

import gg.jte.Content;
import gg.jte.TemplateOutput;
import org.springframework.security.web.csrf.CsrfToken;

/**
 * This class generates an HTML hidden input field containing a CSRF token.
 * It implements the JTE Content interface, allowing it to be used in templates.
 */
public class CsrfHiddenInput implements Content {

    // Stores the CSRF token instance.
    private final CsrfToken csrfToken;

    public CsrfHiddenInput(CsrfToken csrfToken) {
        this.csrfToken = csrfToken;
    }

    @Override
    public void writeTo(TemplateOutput templateOutput) {
        // Ensures the CSRF token is not null before writing it.
        if (this.csrfToken != null) {
            // Generates an HTML hidden input field containing the CSRF token.
            templateOutput.writeContent("<input type=\"hidden\" name=\"%s\" value=\"%s\">"
                    .formatted(csrfToken.getParameterName(), csrfToken.getToken()));
        }
    }
}
