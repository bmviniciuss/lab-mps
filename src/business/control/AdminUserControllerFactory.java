package business.control;

public class AdminUserControllerFactory {
    public static AdminUserController getController() {
        return AdminUserController.getInstance();
    }
}
