package pl.connectis.electronicswebshop.web.registration;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.connectis.electronicswebshop.web.auth.User;
import pl.connectis.electronicswebshop.web.auth.UserService;

@RestController
public class UserRegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/register/{login}/{password}")
    public User addUser(@PathVariable("login") String login,@PathVariable("password") String password){
        return userService.addUser(login,password);
    }





}
