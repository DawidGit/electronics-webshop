package pl.connectis.electronicswebshop.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.persistence.dao.RoleRepository;
import pl.connectis.electronicswebshop.persistence.dao.UserRepository;
import pl.connectis.electronicswebshop.persistence.model.User;
import pl.connectis.electronicswebshop.web.registration.UserDto;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(@Qualifier("postgres") UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User addUser(UserDto userDto, String role) throws UserAlreadyExistException {
        if (usernameExist(userDto.getUsername())) {
            throw new UserAlreadyExistException(
                    "There is an account with that username: "
                            + userDto.getUsername());
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName(role)));
        return userRepository.save(user);
    }

    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

}
