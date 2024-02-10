package be.fourcolors.mvp.model.exceptions;

public class IllegalUsernameException extends IllegalArgumentException {
    public IllegalUsernameException(String message) {
        super(message);
    }
}
