package util;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User not found.");
    }
}
