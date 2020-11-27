package business.control;

public class OrderControllerFactory {
    public static OrderController getController() {
        return OrderController.getInstance();
    }
}
