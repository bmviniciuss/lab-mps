package business.control;

public class UserControllerFactory {
    public static UserController getController() {
        return UserController.getInstance();
    }
}
