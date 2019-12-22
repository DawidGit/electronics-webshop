package pl.connectis.electronicswebshop.web.login;

public interface UserServiceInterface {

    User registerNewUserAccount(UserDto userAccount) throws EmailExistsException;
}