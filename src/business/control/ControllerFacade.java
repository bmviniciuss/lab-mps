package business.control;

import business.model.Date;
import business.model.IUser;
import business.model.User;
import util.InfraException;
import util.UserLoginValidationException;
import util.UserPasswordValidationException;

public class ControllerFacade {
    private static ControllerFacade controllerFacade;
    private UserController userController;

    private ControllerFacade() {
        this.userController = UserControllerFactory.getController();
    }

    public static ControllerFacade getInstance() {
        if(controllerFacade == null) {
            controllerFacade = new ControllerFacade();
        }
        return controllerFacade;
    }


    public IUser createUser(String login, String password, Date birth_date) throws UserLoginValidationException, UserPasswordValidationException, InfraException {
        User toCreateUser = new User(login, password, birth_date);
        this.userController.add(toCreateUser);
        return toCreateUser;
    }

    public void listUser(String mode) throws InfraException {
        if(mode.equals("login")) {
            this.userController.list(new LoginComparator());
        } else if (mode.equals("birth_date")) {
            this.userController.list(new DateComparator());
        }
    }
}
