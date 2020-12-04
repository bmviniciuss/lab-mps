package business.control;

public class CheckoutControllerFactory {
    public static CheckoutController getController() {
        return CheckoutController.getInstance();
    }
}
