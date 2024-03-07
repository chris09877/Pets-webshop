package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/add")
    public String add (@RequestBody User user){
        logger.info("Received new user A ZBEEEEEE: {}", user);

        userService.saveUser(user);
        return "new user added";
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> get (@PathVariable int id)
    {
        logger.info("FIND USER WITH ID:", id);

        User user  = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok().body(user); // Return 200 OK response with the user entity
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found response if user not found
        }
    }
}