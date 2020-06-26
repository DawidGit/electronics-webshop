package pl.connectis.electronicswebshop.web.registration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.connectis.electronicswebshop.persistence.model.User;
import pl.connectis.electronicswebshop.service.UserAlreadyExistException;
import pl.connectis.electronicswebshop.service.UserService;
import pl.connectis.electronicswebshop.web.security.Roles;

import javax.validation.Valid;


@Controller
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registerCustomer")
    public String registerCustomerForm(Model model) {
        return register(model, "Customer");
    }

    @PostMapping("/registerCustomer")
    @ResponseBody
    public ModelAndView registerCustomer(
            @ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, ModelAndView mav) {
        mav.setViewName("register");
        if (bindingResult.hasErrors()) {
            return mav;
        }
        try {
            User registered = userService.addUser(userDto, Roles.ROLE_CUSTOMER.name());
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "user", userDto);
    }

    @GetMapping("/registerEmployee")
    public String registerEmployeeForm(Model model) {
        return register(model, "Employee");
    }

    @PostMapping("/registerEmployee")
    @ResponseBody
    public ModelAndView registerEmployee(
            @ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, ModelAndView mav) {
        mav.setViewName("register");
        if (bindingResult.hasErrors()) {
            return mav;
        }
        try {
            User registered = userService.addUser(userDto, Roles.ROLE_EMPLOYEE.name());

        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }


        return new ModelAndView("successRegister", "user", userDto);
    }

    private String register(Model model, String userType) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("userType", userType);
        return "register";
    }

}
