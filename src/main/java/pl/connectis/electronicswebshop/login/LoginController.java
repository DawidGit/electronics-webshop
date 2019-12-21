package pl.connectis.electronicswebshop.login;

import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    private final LoginRepository loginRepository;


    public LoginController(LoginRepository loginRepository) {this.loginRepository = loginRepository;}

    @GetMapping("/user/{login}")
    public EncryptPass getPassword(@PathVariable String login) {
        return loginRepository.findPassByLogin(login);
    }

    @PostMapping("/newuser")
    public User addUser(@RequestBody User user) { return (User) loginRepository.save(user); }

    @GetMapping("/test")
    public String testString() {return "test";}
    }
