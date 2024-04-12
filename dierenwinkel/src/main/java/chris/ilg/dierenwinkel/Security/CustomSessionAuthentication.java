package chris.ilg.dierenwinkel.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

public class CustomSessionAuthentication implements SessionAuthenticationStrategy {



    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        HttpSession session = request.getSession(false);  // Don't create session if not existing
        if (session == null) {
            session = request.getSession(true);  // Create session only if needed
        }
    }
}

