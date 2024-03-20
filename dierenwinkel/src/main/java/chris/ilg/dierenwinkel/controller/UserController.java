package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/add")
    public ResponseEntity<String> add (@RequestBody User user){
        logger.info("Received new user A ZBEEEEEE: {}", user);
        try
        {
            PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
            String encodedPwd = pwdEncoder.encode(user.getPassword());
            user.setPassword(encodedPwd);
            userService.saveUser(user);
            return ResponseEntity.ok("User added successfully" + user.getId());

        }

        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }    }
    @GetMapping("/{id}")
    public ResponseEntity<User> get (@PathVariable int id)
    {
        logger.info("FIND USER WITH ID:", id);

        User user  = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String mail = credentials.get("mail");
        String pwd = credentials.get("password");
        try {
            // Retrieve user by mail
            User user = userService.getUserByMail(mail);

            if (user == null) {
                // User not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Check if password matches
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean isPasswordMatch = encoder.matches(pwd, user.getPassword());

            if (isPasswordMatch) {
                // Password matches, return user ID
                return ResponseEntity.ok("User ID: " + user.getId());
            } else {
                // Password does not match
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User registrationRequest) {
        // Check if user already exists with the provided email
        if (userService.existByMail(registrationRequest.getMail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
        }

        // Create a new user object
        User user = new User();
        user.setFirstname(registrationRequest.getFirstname());
        user.setLastname(registrationRequest.getLastname());
        user.setAddress(registrationRequest.getAddress());
        user.setPostcode(registrationRequest.getPostcode());
        user.setNumber(registrationRequest.getNumber());
        user.setBirthdate(registrationRequest.getBirthdate());
        user.setPhone(registrationRequest.getPhone());
        user.setMail(registrationRequest.getMail());
        user.setPassword(new BCryptPasswordEncoder().encode(registrationRequest.getPassword()));
        // Set up orders as an empty list for now
        //user.setOrders(new ArrayList<>());

        // Save the user using your user service

        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

}