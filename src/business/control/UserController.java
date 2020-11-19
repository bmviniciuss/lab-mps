package business.control;

import business.exceptions.UserLoginValidationException;
import business.exceptions.UserPasswordValidationException;
import business.model.IUser;

public class UserController {

    private int USER_LOGIN_MAX_LENGTH = 20;
    private int USER_PASSWORD_MIN_LENGTH = 8;
    private int USER_PASSWORD_MAX_LENGTH = 12;


    private void validateUserLogin(IUser user) throws UserLoginValidationException {
        if(user.getLogin().isEmpty()) {
            throw new UserLoginValidationException("A login must me provided.");
        }

        if(user.getLogin().length() > this.USER_LOGIN_MAX_LENGTH) {
            throw new UserLoginValidationException("Login cannot be longer than " + this.USER_LOGIN_MAX_LENGTH);
        }

        if(user.getLogin().matches(".*\\d.*")) {
            throw new UserLoginValidationException("Login cannot contain a number");
        }
    }

    private void validateUserPassword(IUser user) throws UserPasswordValidationException {
        if(user.getPassword().isEmpty()) {
            throw new UserPasswordValidationException("A password must be provided");
        }

        if(user.getPassword().length() > this.USER_PASSWORD_MAX_LENGTH) {
            throw new UserPasswordValidationException("Password cannot be longer than "
                    + this.USER_PASSWORD_MAX_LENGTH);
        }

        if(user.getPassword().length() < this.USER_PASSWORD_MIN_LENGTH) {
            throw new UserPasswordValidationException("Password cannot be smaller than "
                    + this.USER_PASSWORD_MIN_LENGTH);
        }

        if(!(user.getPassword().matches(".*\\d.*\\d.*") && user.getPassword().matches(".*\\w+.*"))) {
            throw new UserPasswordValidationException("Password must have letters and at least 2 numbers");
        }
    }

    private void validateUser(IUser user) throws UserLoginValidationException, UserPasswordValidationException {
        this.validateUserLogin(user);
        this.validateUserPassword(user);
    }

    public void add(IUser toCreateUser) throws UserLoginValidationException, UserPasswordValidationException {
        this.validateUser(toCreateUser);
    }
}
