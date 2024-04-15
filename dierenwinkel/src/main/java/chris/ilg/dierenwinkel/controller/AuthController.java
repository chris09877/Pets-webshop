package chris.ilg.dierenwinkel.controller;


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
    private  HttpSessionCsrfTokenRepository csrfTokenRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            // Retrieve user by email
            CustomUserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getMail());
            logger.info(loginRequest.getPwd() + " userdetails: " + userDetails.getPassword());

            // Compare entered password with hashed password
            if (passwordEncoder.matches(loginRequest.getPwd(), userDetails.getPassword())) {

                logger.info(loginRequest.getPwd() + " userdetails: " + userDetails.getPassword());

                CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
                session.setAttribute("csrfToken", csrfToken.getToken());
                session.setAttribute("userId", userDetails.getId());
                session.setAttribute("user", userDetailsService.loadUserByUsername(loginRequest.getMail()));
                logger.info("csrf token:"+csrfToken.getToken());
                logger.info("user id :"+ userDetails.getId());

                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("csrfToken", csrfToken.getToken());
                responseBody.put("loginSuccess", true); // Indicate successful login
                return ResponseEntity.ok(responseBody);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
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

//    @GetMapping("/login?logout")
//    public String handleLogoutRedirect() {
//
//        return "redirect:/login"; // Redirect to login page after logout
//    }




}
