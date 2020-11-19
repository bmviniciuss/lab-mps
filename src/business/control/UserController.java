package business.control;

import business.exceptions.UserLoginValidationException;
import business.model.IUser;

public class UserController {

    private int USER_LOGIN_MAX_LENGTH = 20;

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
}
