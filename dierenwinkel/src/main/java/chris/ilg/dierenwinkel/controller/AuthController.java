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
//    @Autowired
//    private  HttpSessionCsrfTokenRepository csrfTokenRepository;

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

    @Autowired
    private CsrfTokenRepository csrfTokenRepository;
    private final AuthenticationSuccessHandler successHandler;

    @Autowired
    public AuthController(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request,HttpServletResponse response) {

//        try {
//            // Retrieve user by email
//            CustomUserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getMail());
//            logger.info(loginRequest.getPwd() + " userdetails: " + userDetails.getPassword());
//
//            // Compare entered password with hashed password
//            if (passwordEncoder.matches(loginRequest.getPwd(), userDetails.getPassword())) {
//
//                logger.info(loginRequest.getPwd() + " userdetails: " + userDetails.getPassword());
//
//                CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
//                session.setAttribute("csrfToken", csrfToken.getToken());
//                session.setAttribute("userId", userDetails.getId());
//                session.setAttribute("user", userDetailsService.loadUserByUsername(loginRequest.getMail()));
//                logger.info("csrf token:"+csrfToken.getToken());
//                logger.info("user id :"+ userDetails.getId());
//
//                Map<String, Object> responseBody = new HashMap<>();
//                responseBody.put("csrfToken", csrfToken.getToken());
//                responseBody.put("loginSuccess", true); // Indicate successful login
//                return ResponseEntity.ok(responseBody);
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//            }
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }

//        try {
//            CustomUserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getMail());
//            logger.info(loginRequest.getPwd() + " userdetails: " + userDetails.getPassword());
//
//            // Compare entered password with hashed password
//            if (passwordEncoder.matches(loginRequest.getPwd(), userDetails.getPassword())) {
//                logger.info("Password match");
//                Authentication authentication = authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                loginRequest.getMail(), loginRequest.getPwd()
//                        )
//                );
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                return ResponseEntity.ok().build();  // Success handler will add CSRF token
//            } else {
//                logger.info("Password does not match");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password mismatch");
//            }
//        } catch (AuthenticationException e) {
//            logger.error("Authentication failed", e);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }

//        try {
//            CustomUserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getMail());
//            if (passwordEncoder.matches(loginRequest.getPwd(), userDetails.getPassword())) {
//                Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPwd());
//                SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
//                return ResponseEntity.ok().build(); // Success handler will manage CSRF token
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password mismatch");
//            }
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getMail(), loginRequest.getPwd()
//                    )
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Generate and store CSRF token
//            HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
//            CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
//            csrfTokenRepository.saveToken(csrfToken, request, response);
//            request.getSession().setAttribute("X-CSRF-TOKEN", csrfToken.getToken());
//
//            // Add CSRF token to the response header
//            response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
//
//            // Build response body with CSRF token
//            Map<String, Object> tokenMap = new HashMap<>();
//            tokenMap.put("csrfToken", csrfToken.getToken());
//            return ResponseEntity.ok(tokenMap);
//
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
//        try {
//            CsrfToken csrfToken;
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getMail(), loginRequest.getPwd()
//                    )
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Manage CSRF Token here, ensuring it is only done once
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                 csrfToken = (CsrfToken) session.getAttribute("X-CSRF-TOKEN");
//                if (csrfToken == null) { // Generate and save CSRF token only if not already present
//                    csrfToken = csrfTokenRepository.generateToken(request);
//                    csrfTokenRepository.saveToken(csrfToken, request, response);
//                    session.setAttribute("X-CSRF-TOKEN", csrfToken.getToken());
//                    response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
//                    return ResponseEntity.ok().header("X-CSRF-TOKEN", csrfToken.getToken()).build();
//
//                }
//            }
//
//
////            return ResponseEntity.ok().header("X-CSRF-TOKEN", csrfToken.getToken()).build();
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
       // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getMail(), loginRequest.getPwd()
//                    )
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
////            // Generate CSRF token and set it in the session and response only if authentication is successful
////            HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
////            CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
////            csrfTokenRepository.saveToken(csrfToken, request, response);
////            request.getSession().setAttribute("X-CSRF-TOKEN", csrfToken.getToken());
////            logger.info("the tokjen in login controller: "+ csrfToken.getToken());
//            //response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
//
//            return ResponseEntity.ok().build();//.header("X-CSRF-TOKEN", csrfToken.getToken()).build();
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getMail(), loginRequest.getPwd()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // After successful authentication, generate and send CSRF token
//            CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
//            csrfTokenRepository.saveToken(csrfToken, request, response);
//            response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
            try {
                successHandler.onAuthenticationSuccess(request, response, authentication);
            } catch (IOException | ServletException e) {
                // Handle exceptions
            }

            return ResponseEntity.ok().build();//.header("X-CSRF-TOKEN", csrfToken.getToken()).build();
        } catch (AuthenticationException e) {
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

//    @GetMapping("/login?logout")
//    public String handleLogoutRedirect() {
//
//        return "redirect:/login"; // Redirect to login page after logout
//    }




}
