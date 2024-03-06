package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add (User user){
        userService.saveUser(user);
        return "new user added";
    }

}