package util;

public class UserPasswordValidationException extends Exception{
    public UserPasswordValidationException(String errorMessage) {
        super(errorMessage);
    }
}
