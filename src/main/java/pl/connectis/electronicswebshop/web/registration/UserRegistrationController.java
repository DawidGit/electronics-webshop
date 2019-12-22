package pl.connectis.electronicswebshop.web.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.connectis.electronicswebshop.persistence.dao.RoleRepository;
import pl.connectis.electronicswebshop.persistence.model.User;
import pl.connectis.electronicswebshop.service.UserService;

import java.util.Collections;

@RestController
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register/{username}/{password}")
    public String addUser(@PathVariable("username") String username, @PathVariable("password") String password) {

        userService.addUser(new User(
                passwordEncoder.encode(password),
                username,
                Collections.singletonList(roleRepository.findByName("ROLE_CUSTOMER")),
                "test@test.com"));

        return "Zarejestrowano użytkownika: " + username;
    }





}
