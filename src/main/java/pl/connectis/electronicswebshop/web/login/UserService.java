package pl.connectis.electronicswebshop.web.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
        "There is an account with that email adress: " +  accountDto.getEmail());
        }

        User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList("ROLE_USER"));
        return repository.save(user);
        // the rest of the registration operation
    }
    private boolean emailExist(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}