package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}