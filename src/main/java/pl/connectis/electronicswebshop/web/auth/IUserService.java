package pl.connectis.electronicswebshop.web.auth;

public interface IUserService {

    User addUser(String login, String password);
}