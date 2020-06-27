package pl.connectis.electronicswebshop.web.registration;


import lombok.Data;
import pl.connectis.electronicswebshop.web.validation.PasswordMatches;
import pl.connectis.electronicswebshop.web.validation.ValidEmail;

import javax.validation.constraints.NotBlank;

@Data
@PasswordMatches
public class UserDto {

    @NotBlank(message = "Pole nie może być puste")
    private String username;

    @NotBlank(message = "Pole nie może być puste")
    private String firstName;

    @NotBlank(message = "Pole nie może być puste")
    private String lastName;

    @NotBlank(message = "Pole nie może być puste")
    private String password;
    private String matchingPassword;

    @ValidEmail
    private String email;
}
