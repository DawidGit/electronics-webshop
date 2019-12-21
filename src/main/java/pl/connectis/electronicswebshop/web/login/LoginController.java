package pl.connectis.electronicswebshop.web.login;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
public class LoginController {
    private final UserRepository userRepository;


    public LoginController(UserRepository userRepository) {this.userRepository = userRepository;}

    @GetMapping("/user/{login}")
    public EncryptPass getPassword(@PathVariable String login) {
        return userRepository.findPassByLogin(login);
    }

    @PostMapping("/newuser")
    public User addUser(@RequestBody User user) { return (User) userRepository.save(user); }

    @GetMapping("/test")
    public List<String> publicHello() {
        return Arrays.asList("Hello", "World", "from", "Public");
    }
}
