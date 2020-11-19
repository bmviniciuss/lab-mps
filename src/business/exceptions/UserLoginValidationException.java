package business.exceptions;

public class UserLoginValidationException extends Exception{
    public UserLoginValidationException(String errorMessage) {
        super(errorMessage);
    }
}
