package pl.connectis.electronicswebshop.web.registration;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}
