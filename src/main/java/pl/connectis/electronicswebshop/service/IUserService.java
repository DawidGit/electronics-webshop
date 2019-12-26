package pl.connectis.electronicswebshop.service;

import pl.connectis.electronicswebshop.persistence.model.User;
import pl.connectis.electronicswebshop.web.registration.UserDto;

public interface IUserService {

    void addUser(User user);

    void addUser(UserDto userDto);
}
