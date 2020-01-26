package pl.connectis.electronicswebshop.service;


import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
public class UserService implements IUserService {

    @Autowired
    @Qualifier("postgres")
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    public void addCustomerUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_CUSTOMER")));
        userRepository.save(user);
    }

    public void addEmployeeUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_EMPLOYEE")));
        userRepository.save(user);
    }

}
