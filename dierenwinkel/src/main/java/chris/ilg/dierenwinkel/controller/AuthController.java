package chris.ilg.dierenwinkel.controller;


import chris.ilg.dierenwinkel.Security.CustomSessionAuthentication;
import chris.ilg.dierenwinkel.Security.CustomUserDetails;
import chris.ilg.dierenwinkel.Security.CustomUserDetailsService;
import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.service.LoginRequest;
import chris.ilg.dierenwinkel.service.UserDto;
import chris.ilg.dierenwinkel.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpSession session;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationSuccessHandler successHandler;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request,HttpServletResponse response) {

        try {
            CustomUserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getMail());
            if (passwordEncoder.matches(loginRequest.getPwd(), userDetails.getPassword())) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPwd(), userDetails.getAuthorities());
                Authentication authenticated = authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(authenticated);

                try {
                    // Use autowired success handler
                    successHandler.onAuthenticationSuccess(request, response, authenticated);
                }catch (ServletException e) {
                    logger.error("Error during post-authentication: {}", e.getMessage(), e);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login processing error");
                }
                int userId = userDetails.getId(); // Assuming getId() is implemented to return user ID
                response.setHeader("User-ID", String.valueOf(userId));
                return ResponseEntity.ok("Login successful");
            } else {
                logger.info("Password does not match");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password mismatch");
            }
        } catch (AuthenticationException | IOException e) {
            logger.error("Authentication failed", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().stream()
                    .map(error -> (FieldError) error)  // Cast to FieldError
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }

        User savedUser = userServiceImpl.saveUser(userDto);
        UserDto savedUserDto = new UserDto(savedUser.getId());
        return ResponseEntity.ok(savedUserDto);
    }





}
