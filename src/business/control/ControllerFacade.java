package business.control;

import business.model.Date;
import business.model.IUser;
import business.model.User;
import util.InfraException;
import util.UserLoginValidationException;
import util.UserNotFoundException;
import util.UserPasswordValidationException;


public class ControllerFacade {
    private static ControllerFacade controllerFacade;
    private UserController userController;
    private OrderController orderController;

    private ControllerFacade() {
        this.userController = UserControllerFactory.getController();
        this.orderController = OrderControllerFactory.getController();
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

    public void findUserByLogin(String login) throws UserNotFoundException, InfraException {
        this.userController.listSingleUser(login);
    }

    public void removeUserByLogin(String login) throws UserNotFoundException, InfraException {
        this.userController.delete(login);
    }

    public void listOrders() throws InfraException {
        this.orderController.list();
    }
}