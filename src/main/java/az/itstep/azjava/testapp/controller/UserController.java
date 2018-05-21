package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @PostMapping
    User save(@RequestBody User user) {
        return userService.save(user);
    }


    @PutMapping
    String signIn(@RequestBody User user) {
        return userService.signIn(user);
    }

    @ExceptionHandler(RuntimeException.class)
    String handle (RuntimeException e){
        return e.getMessage();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
