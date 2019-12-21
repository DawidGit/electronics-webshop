package pl.connectis.electronicswebshop.web.login;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}
