package pl.connectis.electronicswebshop.web.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.connectis.electronicswebshop.service.UserService;


@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registerCustomer")
    public String registerCustomerForm(Model model) {

        UserDto user = new UserDto();

        model.addAttribute("user", user);

        return "registerCustomer";
    }

    @PostMapping("/registerCustomer")
    @ResponseBody
    public String registerCustomer(
            @ModelAttribute("user") UserDto user
    ) {

        userService.addUser(user);

        return "zarejestrowano użytkownika " + user.getUsername();
    }

}
